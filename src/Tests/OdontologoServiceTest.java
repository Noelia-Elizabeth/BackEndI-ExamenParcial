package Tests;

import Proyecto.dao.impl.OdontologoDaoH2;
import Proyecto.model.Odontologo;
import Proyecto.service.OdontologoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OdontologoServiceTest {

    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    private static final Logger logger = Logger.getLogger(String.valueOf(OdontologoServiceTest.class));

    @Before
    public void cargarOdontologos() {
        Odontologo odontologo1 = new Odontologo("ADF-001","Alejandra","Somonte");
        Odontologo odontologo2 = new Odontologo("UIO-873","Carla","Rodriguez");
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
    }

    @Test
    public void agregarOdontologo() {
        Odontologo odontologo = new Odontologo("JWK-918","Ross", "Geller");
        odontologoService.guardarOdontologo(odontologo);
        Assert.assertTrue(odontologoService.buscarTodos().contains(odontologo));
    }

    @Test
    public void listarOdontologos() {
        ArrayList<Odontologo> odontologos = odontologoService.buscarTodos();
        //Assert.assertFalse(odontologos.isEmpty());
        Assert.assertEquals(2, odontologos.size());
    }


}
