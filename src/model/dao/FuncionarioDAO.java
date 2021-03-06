package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Funcionario;

public class FuncionarioDAO {
    Connection con;
    
    public FuncionarioDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void create(Funcionario f) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into funcionario " +
				"(Matricula, CPF, Login, Senha, Nome, Email, CargaHoraria, MatriculaSupervisor, IdJornada, DataInicio, CodigoUnidadeDeSuporte) " +
				"values (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getMatricula());
			stmt.setLong(2, f.getCPF());
			stmt.setString(3, f.getLogin());
			stmt.setString(4, f.getSenha());
			stmt.setString(5, f.getNome());
			stmt.setString(6, f.getEmail());
			stmt.setInt(7, f.getCargaHoraria());
			stmt.setString(8, f.getMatriculaSuperv());
			stmt.setString(9, f.getIdjornada());
			stmt.setString(10, f.getDataInicio());
			stmt.setInt(11, f.getCodUnid_Suporte());
			
			stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Funcionario> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setMatricula(rs.getString("Matricula"));
                funcionario.setCPF(rs.getLong("CPF"));
                funcionario.setLogin(rs.getString("Login"));
                funcionario.setSenha(rs.getString("Senha"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setCargaHoraria(rs.getInt("CargaHoraria"));
				funcionario.setMatriculaSuperv(rs.getString("MatriculaSupervisor"));
				funcionario.setData_inicio(rs.getString("DataInicio"));
				funcionario.setCodUnid_Suporte(rs.getInt("CodigoUnidadeDeSuporte"));
				funcionario.setId_jornada(rs.getString("IdJornada"));
				

				funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }
    public Funcionario readForMat(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;
        

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE Matricula LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
            	funcionario = new Funcionario();
            	
            	funcionario.setMatricula(rs.getString("Matricula"));
                funcionario.setCPF(rs.getLong("CPF"));
                funcionario.setLogin(rs.getString("Login"));
                funcionario.setSenha(rs.getString("Senha"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setCargaHoraria(rs.getInt("CargaHoraria"));
				funcionario.setMatriculaSuperv(rs.getString("MatriculaSupervisor"));
				funcionario.setData_inicio(rs.getString("DataInicio"));
				funcionario.setCodUnid_Suporte(rs.getInt("CodigoUnidadeDeSuporte"));
				funcionario.setId_jornada(rs.getString("IdJornada"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcionario;

    }

    public void update(Funcionario f) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET Matricula = ? ,CPF = ?,Login = ?,Senha = ?,Nome = ?, Email = ?, CargaHoraria = ?, MatriculaSupervisor = ?, IdJornada = ?, DataInicio = ?, CodigoUnidadeDeSuporte = ?  WHERE Matricula = ?");
            stmt.setString(1, f.getMatricula());
			stmt.setLong(2, f.getCPF());
			stmt.setString(3, f.getLogin());
			stmt.setString(4, f.getSenha());
			stmt.setString(5, f.getNome());
			stmt.setString(6, f.getEmail());
			stmt.setInt(7, f.getCargaHoraria());
			stmt.setString(8, f.getMatriculaSuperv());
			stmt.setString(9, f.getIdjornada());
			stmt.setString(10, f.getDataInicio());
			stmt.setInt(11, f.getCodUnid_Suporte());
			stmt.setString(1, "%"+f.getMatricula()+"%");
			
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Funcionario f) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE Matricula = ?");
            stmt.setString(1, f.getMatricula());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
