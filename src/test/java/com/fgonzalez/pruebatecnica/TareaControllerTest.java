package com.fgonzalez.pruebatecnica;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fgonzalez.pruebatecnica.persistance.entity.Tarea;
import com.fgonzalez.pruebatecnica.persistance.repository.TareaRepository;

public class TareaControllerTest extends AbstractControllerTest {

    @MockBean
    private TareaRepository tareaRepository;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Tarea tarea1;
    private Tarea tarea2;
    private Tarea nuevaTarea;

    @BeforeEach
    protected void setUp() throws ParseException {
        super.setUp();
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        tarea1 = Tarea.builder().identificador(1).descripcion("Tarea prueba 1").vigente(true).fechaCreacion(new Timestamp(dateFormat.parse("2023-01-02 08:00:00").getTime())).build();
        tarea2 = Tarea.builder().identificador(2).descripcion("Tarea prueba 2").vigente(true).fechaCreacion(new Timestamp(dateFormat.parse("2023-01-02 08:01:00").getTime())).build();
        nuevaTarea = Tarea.builder().identificador(3).descripcion("Tarea nueva").vigente(true).fechaCreacion(new Timestamp(System.currentTimeMillis())).build();
        
        doReturn(Arrays.asList(tarea1, tarea2)).when(tareaRepository).findAll();
        when(tareaRepository.findById(1)).thenReturn(Optional.of(tarea1));
        when(tareaRepository.findById(2)).thenReturn(Optional.of(tarea2));
        when(tareaRepository.save(nuevaTarea)).thenReturn(nuevaTarea);
        when(tareaRepository.save(tarea1)).thenReturn(tarea1);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(tareaRepository).save(any());
    }

    @Test
    @DisplayName("Prueba la obtencion de tareas")
    public void testGetTareas() throws Exception {
        MvcResult mvcResult = mvc
                .perform(
                        MockMvcRequestBuilders.get("/tarea/gettareas/").accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertArrayEquals(new Tarea[] { tarea1, tarea2 }, mapFromJson(content, Tarea[].class),
                "La respuesta debe tener tareas");
    }

    @Test
    @DisplayName("Prueba de eliminacion de tarea")
    public void testRemoveTarea() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/tarea/removetarea")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(tarea1))).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(),
                "Expected operation was correct");
    }

    @Test
    @DisplayName("Prueba de actualizacion de tarea, validacion de identificador")
    public void testUpdateTarea() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("identificador", null);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/tarea/updatetarea")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(payload))).andReturn();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus(),
                "Expected error on server side");
    }

    @Test
    @DisplayName("Prueba de agregar una tarea, validacion de identificador")
    public void testAddTarea() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("descripcion", null);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/tarea/addtarea")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(payload))).andReturn();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus(),
                "Expected error on server side");
    }
}
