/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Paciente;
import persistencia.ConexaoBanco;

public class PacienteDAO {

    private ConexaoBanco conexao;
    private Connection con;

    public PacienteDAO() {
        this.conexao = new ConexaoBanco();
    }

    // método cadastrarPaciente
    public void cadastrarPaciente(Paciente pac) throws SQLException {
        try {
            con = conexao.getConexao();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String sql = "insert into PACIENTE(NOME, ENDERECO, DATA_NASC, TELEFONE, CPF, RG, ID_CONVENIO_FK, EMAIL) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = this.con.prepareStatement(sql);
            pst.setString(1, pac.getNome());
            pst.setString(2, pac.getEndereco());
            pst.setString(3, sdf.format(pac.getDataNascimento()));
            pst.setString(4, pac.getTelefone());
            pst.setString(5, pac.getCpf());
            pst.setString(6, pac.getRg());
            pst.setInt(7, pac.getIdConvenio());
            pst.setString(8, pac.getEmail());
            pst.execute();
        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir dados no Banco de Dados! " + se.getMessage());
        } finally {
            con.close();
        }
    }

    // método buscarPaciente com condição
    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {
        ResultSet rs;
        try {
            String sql = "SELECT * FROM paciente " + query;
            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            ArrayList<Paciente> pacientes = new ArrayList<>();
            while (rs.next()) {
                Paciente pac = new Paciente();
                pac.setIdPaciente(rs.getInt("ID_PACIENTE"));
                pac.setNome(rs.getString("NOME"));
                pac.setEndereco(rs.getString("ENDERECO"));
                pac.setDataNascimento(rs.getDate("DATA_NASC"));
                pac.setTelefone(rs.getString("TELEFONE"));
                pac.setCpf(rs.getString("CPF"));
                pac.setRg(rs.getString("RG"));
                pac.setIdConvenio(rs.getInt("ID_CONVENIO_FK"));
                pac.setEmail(rs.getString("EMAIL"));
                pacientes.add(pac);
            }
            return pacientes;
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            con.close();
        }
    }

    // método verificar CPF duplicado
    public boolean verificarCpfDuplicado(String cpf) {
        Connection con = null;
        try {
            con = conexao.getConexao();
            String sql = "SELECT COUNT(*) FROM paciente WHERE cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o CPF já estiver registrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<Paciente> buscarPaciente() throws SQLException {
        ResultSet rs;
        try {
            String sql = "SELECT * FROM PACIENTE";
            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            ArrayList<Paciente> pacientes = new ArrayList<>();
            while (rs.next()) {
                Paciente pac = new Paciente();
                pac.setIdPaciente(rs.getInt("ID_PACIENTE"));
                pac.setNome(rs.getString("NOME"));
                pac.setEndereco(rs.getString("ENDERECO"));
                pac.setDataNascimento(rs.getDate("DATA_NASC"));
                pac.setTelefone(rs.getString("TELEFONE"));
                pac.setCpf(rs.getString("CPF"));
                pac.setRg(rs.getString("RG"));
                pac.setIdConvenio(rs.getInt("ID_CONVENIO_FK"));
                pac.setEmail(rs.getString("EMAIL"));
                pacientes.add(pac);
            }
            return pacientes;
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            con.close();
        }
    }
}
