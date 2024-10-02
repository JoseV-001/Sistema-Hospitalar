/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package servicos;

import dao.PacienteDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Paciente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JoseV
 */
public class PacienteServicosTest {
    
    private PacienteDAO pd = new PacienteDAO();
    
    public PacienteServicosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
    @Test
   public void testCadastrarSucesso() throws ParseException, SQLException{
              
       Paciente pac = new Paciente();
       
       pac.setNome("Teste");
       pac.setCpf("123.456.111-23");
       pac.setRg("1246763");
       pac.setEndereco("rua");
       pac.setTelefone("(21) 98002-5862");
       pac.setEmail("kX@gmail.com");
       pac.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2004"));
       pac.setIdConvenio(1);
      
       pd.cadastrarPaciente(pac);
       
       assertNotNull(pac);
       
       assertEquals(Paciente.class,pac.getClass());
       
       assertEquals(pac.getNome(),"Teste");
       assertEquals(pac.getCpf(),"123.456.111-23");
       assertEquals(pac.getRg(),"1246763");
       assertEquals(pac.getEndereco(),"rua");
       assertEquals(pac.getTelefone(),"(21) 98002-5862");
       assertEquals(pac.getEmail(),"kX@gmail.com");
       assertEquals(pac.getDataNascimento(),new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2004"));
       assertEquals(pac.getIdConvenio(),1);
       
   }

   
 @Test
public void testVerificarCpfDuplicado() throws SQLException, ParseException {
    PacienteDAO dao = new PacienteDAO();

    // Cadastro de paciente
    Paciente pac1 = new Paciente();
    pac1.setNome("Teste1");
    pac1.setCpf("123.456.111-23");
    pac1.setRg("1246763");
    pac1.setEndereco("Rua Exemplo 1");
    pac1.setTelefone("(21) 98002-5862");
    pac1.setEmail("teste1@gmail.com");
    pac1.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2004"));
    pac1.setIdConvenio(1);

    dao.cadastrarPaciente(pac1);  // Cadastra o primeiro paciente

    // Verificar se CPF já existe
    boolean cpfDuplicado = dao.verificarCpfDuplicado("123.456.111-23");

    // O CPF deve estar duplicado
    assertTrue("O CPF deveria estar cadastrado", cpfDuplicado);
}  
   
   
@Test
public void testVerificarCpfInexistente() throws SQLException, ParseException {
    PacienteDAO dao = new PacienteDAO();

    // Verificar se CPF que não existe retorna false
    boolean cpfDuplicado = dao.verificarCpfDuplicado("999.999.999-99"); // CPF não cadastrado
    assertFalse("O CPF não deveria estar cadastrado", cpfDuplicado);
}


   
}
