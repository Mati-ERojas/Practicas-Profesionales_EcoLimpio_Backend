package com.ecolimpio.ecommerce.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecolimpio.ecommerce.models.entities.Categoria;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService extends BaseService<Producto, String> {
    public ProductoService(ProductoRepository productoRepository) {
        super(productoRepository);
    }

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    @Transactional
    public Producto create(Producto producto) throws Exception {
        try {
            MultipartFile archivo = producto.getArchivo();

            if (archivo != null && !archivo.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(archivo.getBytes(), ObjectUtils.emptyMap());
                producto.setUrlImagen(uploadResult.get("secure_url").toString());
                producto.setPublicId(uploadResult.get("public_id").toString());
            }
            return productoRepository.save(producto);
        } catch (IOException e) {
            throw new Exception("Error al subir imagen del producto a Clodinary " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Producto update(Producto productoActualizado) throws Exception {
        try {
            MultipartFile nuevoArchivo = productoActualizado.getArchivo();

            if (nuevoArchivo != null && !nuevoArchivo.isEmpty()) {
                if (productoActualizado.getPublicId() != null && !productoActualizado.getPublicId().isEmpty()) {
                    cloudinary.uploader().destroy(productoActualizado.getPublicId(), ObjectUtils.emptyMap());
                }
                Map uploadResult = cloudinary.uploader().upload(nuevoArchivo.getBytes(), ObjectUtils.emptyMap());
                productoActualizado.setUrlImagen(uploadResult.get("secure_url").toString());
                productoActualizado.setPublicId(uploadResult.get("public_id").toString());
            }

            return productoRepository.save(productoActualizado);
        } catch (IOException e) {
            throw new Exception("Error al subir imagen del producto a Clodinary " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        try {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new Exception("Producto no encontrado"));

            if (producto.getPublicId() != null && !producto.getPublicId().isEmpty()) {
                cloudinary.uploader().destroy(producto.getPublicId(), ObjectUtils.emptyMap());
            }
            productoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Producto agregarCategoria(String idProducto, Categoria categoria) throws Exception {
        try {
            Producto producto = productoRepository.findById(idProducto).orElse(null);
            if (producto != null) {
                producto.setCategoria(categoria);
                productoRepository.save(producto);
                return producto;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Producto> listarPorCategoria(String idCategoria) throws Exception {
        try {
            return productoRepository.findAllByCategoriaId(idCategoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
