package ec.edu.ups.poo.dao.impl;

import ec.edu.ups.poo.dao.ProductoDAO;
import ec.edu.ups.poo.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDAOMemoria implements ProductoDAO {
    private final List<Producto> productos;

    public ProductoDAOMemoria() {
        productos = new ArrayList<>();
        crear(new Producto(1, "Jabon", 4.55));
        crear(new Producto(2, "Desinfectante", 3.40));
        crear(new Producto(3, "Cepillo de Dientes", 7.25));
        crear(new Producto(4, "Shampoo", 14.50));
        crear(new Producto(5, "Serum", 23.45));
        crear(new Producto(6, "Crema Corporal", 8.55));
        crear(new Producto(7, "Acondicionador", 17.75));
    }

    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }
}