package Proyecto.dao;

import java.util.ArrayList;

public interface IDao<T> {

    public T guardar(T t);
    public ArrayList<T> buscarTodos();

    /*En este caso solo tenemos a la clase Odontologo para usar esta interfaz,
    pero al hacerlo genérico se vuelva más facilmente escalable. */
}
