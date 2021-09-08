//package com.example.cmdmapi.controller;
//
//
//import com.example.cmdmapi.controller.ReportController;
//import com.example.cmdmapi.model.Report;
//import com.example.cmdmapi.service.ReportService;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
//import static org.mockito.Mockito.when;
//
//@WebMvcTest
//public class ReportControllerTest{
//
//    @Autowired
//    private ReportController reportController;
//
//    @MockBean
//    private ReportService reportService;
//
//    @BeforeEach
//    public void setUp(){
//        RestAssuredMockMvc.standaloneSetup(this.reportController);
//    }
//
//    @Test
//    public void deveRetornarSucesso_QuandoObterRelatos() {
//
//       when(this.reportService.obterRelatos(1L))
//               .thenReturn(new Report(1L,
//                       "relato relato", "teste teste"));
//
//        given()
//                .accept(ContentType.JSON)
//        .when()
//            .get("/report/{id}", 1L)
//            .then()
//            .statusCode(HttpStatus.OK.value());
//
//
//    }
//
//    @Test
//    public void deveRetornarNaoEncontrado_QuandoObterRelatos(){
//        when(this.reportService.obterRelatos(5L))
//                .thenReturn(null);
//        given()
//                .accept(ContentType.JSON)
//        .when()
//        .get("/report{id}", 5L)
//                .then()
//                .statusCode(HttpStatus.NOT_FOUND.value());
//
//    }
//
//    /*
//
//    @Test
//    public void deveRetornarSucesso_QuandoCadastarRelatos(){
//        when(this.reportService.cadastrarRelatos(reports))
//                .thenReturn(null);
//
//        RestAssuredMockMvc.given()
//                .accept(ContentType.JSON)
//                .when()
//                .get("/report/{id}", 1L)
//                .then()
//                .statusCode(HttpStatus.OK.value());
//    }*/
//}