package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoEliminarView;
import ec.edu.ups.vista.ProductoListaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoEliminarView productoEliminarView;

    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoEliminarView productoEliminarView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoEliminarView = productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;
        this.configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {

        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoAnadirView.getBtnCerrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoAnadirView.dispose();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });

        productoEliminarView.getCboxForma().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurarBusquedaDinamica();
            }
        });

        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) productoEliminarView.getCboxForma().getSelectedItem();

                if ("Nombre".equals(seleccion)) {
                    buscarProductoEliminar();
                } else if ("Codigo".equals(seleccion)) {
                    buscarProductoPorCodigoEliminar();
                }
            }
        });


        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        productoEliminarView.getBtnCerrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoEliminarView.dispose();
            }
        });
    }

    private void eliminarProducto() {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que deseas eliminar este producto?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            String seleccion = (String) productoEliminarView.getCboxForma().getSelectedItem();

            if ("Nombre".equals(seleccion)) {
                String nombre = productoEliminarView.getTxtNombre().getText().trim();
                List<Producto> lista = productoDAO.buscarPorNombre(nombre);
                if (!lista.isEmpty()) {
                    productoDAO.eliminar(lista.get(0).getCodigo());
                    productoEliminarView.getTxtAreaImprimir().setText("");
                } else {
                    productoEliminarView.getTxtAreaImprimir().setText("Producto no encontrado para eliminar.");
                }
                productoEliminarView.getTxtCodigo().setText("");
                productoEliminarView.getTxtNombre().setText("");
            } else if ("Codigo".equals(seleccion)) {
                try {
                    int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText().trim());
                    Producto producto = productoDAO.buscarPorCodigo(codigo);
                    if (producto != null) {
                        productoDAO.eliminar(codigo);
                        productoEliminarView.getTxtAreaImprimir().setText("");
                    } else {
                        productoEliminarView.getTxtAreaImprimir().setText("Producto no encontrado para eliminar.");
                    }
                    productoEliminarView.getTxtCodigo().setText("");
                    productoEliminarView.getTxtNombre().setText("");
                } catch (NumberFormatException ex) {
                    productoEliminarView.getTxtAreaImprimir().setText("Código inválido para eliminar.");
                }
            }
        }
    }


    private void configurarBusquedaDinamica() {
        String seleccion = (String) productoEliminarView.getCboxForma().getSelectedItem();

        if ("Codigo".equals(seleccion)) {
            productoEliminarView.getCodigo().setVisible(true);
            productoEliminarView.getTxtCodigo().setVisible(true);
            productoEliminarView.getBtnBuscar().setEnabled(true);
        } else if ("Nombre".equals(seleccion)) {
            productoEliminarView.getNombre().setVisible(true);
            productoEliminarView.getTxtNombre().setVisible(true);
            productoEliminarView.getBtnBuscar().setEnabled(true);
        }
    }


    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void buscarProductoEliminar() {
        String nombre = productoEliminarView.getTxtNombre().getText().trim();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);

        if (productosEncontrados.isEmpty()) {
            productoEliminarView.getTxtAreaImprimir().setText("No se encontró ningún producto.");
        } else {
            StringBuilder resultado = new StringBuilder();
            for (Producto p : productosEncontrados) {
                resultado.append(p).append("\n");
            }
            productoEliminarView.getTxtAreaImprimir().setText(resultado.toString());
        }
    }


    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    private void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontro el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }

    private void buscarProductoPorCodigoEliminar() {
        try {
            int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);

            if (producto == null) {
                productoEliminarView.getTxtAreaImprimir().setText("No se encontró el producto.");
            } else {
                productoEliminarView.getTxtAreaImprimir().setText(producto.toString());
            }
        } catch (NumberFormatException ex) {
            productoEliminarView.getTxtAreaImprimir().setText("Código inválido.");
        }
    }

}