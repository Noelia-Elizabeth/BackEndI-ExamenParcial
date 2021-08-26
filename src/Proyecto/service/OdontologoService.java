package Proyecto.service;

import Proyecto.dao.IDao;
import Proyecto.model.Odontologo;
import java.util.ArrayList;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public ArrayList<Odontologo> buscarTodos() {
        return odontologoIDao.buscarTodos();
    }
}
