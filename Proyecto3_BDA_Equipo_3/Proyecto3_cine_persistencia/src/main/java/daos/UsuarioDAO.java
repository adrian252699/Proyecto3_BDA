/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import config.ConexionMongo;
import entidades.Usuario;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import interfaces.IUsuarioDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class UsuarioDAO implements IUsuarioDAO{
    
    private final MongoCollection<Usuario> coleccion;
    private final MongoCollection<Document> coleccionDoc;

    public UsuarioDAO() {
        this.coleccion = ConexionMongo.getCollection(
            "usuarios",
            Usuario.class
        );
        
        this.coleccion.createIndex(
            Indexes.ascending("email"),
            new IndexOptions().unique(true)
        );
        
        this.coleccionDoc = ConexionMongo.getCollection(
            "usuarios",
            Document.class
        );
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws DaoException {
        try {
            if (usuario==null) {
                throw new IllegalArgumentException("El usuario no puede estar vacio");
            }
            
            Instant now = Instant.now();
            usuario.setCreatedAt(now);
            usuario.setUpdatedAt(now);
            
            this.coleccion.insertOne(usuario);
            
            return usuario;
        } catch (MongoException e) {
            throw new DaoException("Error al guardar usuario",e);
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws DaoException,EntityNotFoundException {
        try {
            if (usuario == null || usuario.getId() == null) {
                throw new IllegalArgumentException("Usuario inválido");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", usuario.getId()),
                combine(
                        set("nombre", usuario.getNombre()),
                        set("apellidoMaterno",usuario.getApellidoMaterno()),
                        set("apellidoPaterno",usuario.getApellidoPaterno()),
                        set("telefono",usuario.getTelefono()),
                        set("fechaNacimiento",usuario.getFechaNacimiento()),
                        set("updatedAt",now)
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                throw new EntityNotFoundException("No se encontró el usuario para actualizar");
            }
            
            return coleccion.find(eq("_id", usuario.getId())).first();
            
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar usuario",e);
        }
    }
    
    @Override
    public boolean activarUsuario(ObjectId id) throws DaoException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Usuario inválido");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", id),
                combine(
                        set("activo",true),
                        set("updatedAt",now)
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                return false;
            }
            
            return resultado.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error al activar usuario",e);
        }
    }

    @Override
    public boolean desactivarUsuario(ObjectId id) throws DaoException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Usuario inválido");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", id),
                combine(
                        set("activo",false),
                        set("updatedAt",now)
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                return false;
            }
            
            return resultado.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error al desactivar usuario",e);
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws DaoException, EntityNotFoundException {
        try {
            if (correo == null || correo.trim().isEmpty()) {
                throw new IllegalArgumentException("Correo requerido");
            }
            
            Usuario usuario = coleccion.find(Filters.eq("email",correo)).first();
            if (usuario == null) {
                throw new EntityNotFoundException("No se encontró un usuario con ese correo");
            }
            
            return usuario;
            
        } catch (MongoException e) {
            throw new DaoException("No fue buscar por correo", e);
        }
    }

    @Override
    public Usuario buscarPorId(ObjectId id) throws DaoException, EntityNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("Id Requerido");
        }
        
        try {
            Usuario usuario = coleccion.find(eq("_id", id)).first();

            if (usuario == null) {
                throw new EntityNotFoundException("Usuario no encontrado");
            }

            return usuario;
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible buscar el usuario", e);
        }
    }

    @Override
    public boolean existeCorreo(String correo) throws DaoException {
        try {

            if (correo == null || correo.trim().isEmpty()) {
                throw new IllegalArgumentException("Correo requerido");
            }

            long cantidadCorreos = coleccion.countDocuments(eq("email", correo));

            return cantidadCorreos > 0;

        } catch (MongoException e) {
            throw new DaoException("No fue posible verificar el correo",e);
        }    
    }

    @Override
    public List<Usuario> listarUsuarios() throws DaoException {
        try {
            
            List<Usuario> listaUsuario = coleccion.find().into(new ArrayList<>());
            
            if (listaUsuario.isEmpty()) {
                return new ArrayList<>();
            }
            
            return listaUsuario;
        } catch (MongoException e) {
            throw new DaoException("No fue posible listar todas los usuarios", e);
        }
    }

    @Override
    public List<Usuario> listarUsuariosPaginado(int pagina, int limite) throws DaoException {
        try {
            int skip = (pagina - 1) * limite;
            
            List<Bson> pipeline = Arrays.asList(
                Aggregates.sort(Sorts.descending("createdAt")),
                Aggregates.skip(skip),
                Aggregates.limit(limite)
            );
            
            return coleccion.aggregate(pipeline).into(new ArrayList<>());
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible paginar los usuarios", e);
        }
    }

    @Override
    public Usuario actualizarPassword(ObjectId id, String passwordHash) throws DaoException,EntityNotFoundException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id inválido");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", id),
                combine(
                        set("passwordHash", passwordHash),
                        set("updatedAt",now)
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                throw new EntityNotFoundException("No se encontró el usuario para actualizar");
            }
            
            return coleccion.find(eq("_id", id)).first();
            
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar password",e);
        }
    }

    @Override
    public Usuario actualizarCorreo(ObjectId id, String correo) throws DaoException,EntityNotFoundException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id inválido");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", id),
                combine(
                        set("email", correo),
                        set("updatedAt",now)
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                throw new EntityNotFoundException("No se encontró el usuario para actualizar");
            }
            
            return coleccion.find(eq("_id", id)).first();
            
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar correo",e);
        }
    }
}