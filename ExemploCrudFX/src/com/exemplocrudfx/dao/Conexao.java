package com.exemplocrudfx.dao;

	import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

	public class Conexao
	{
	    //usuário e senha
	       private String login = "root";
	      private String senha = "123";
	   // IP do servidor do banco
	      private String host = "localhost";
	   // Nome do banco de dados
	      private String dbName = "doutorado_bd";
	   // URL de conexao do banco
	      private String url = "jdbc:mysql://" + host + "/" + dbName;
	  // Objeto do tipo Connection para estabelecer a conexão
	      public Connection conexao = null;
	  // Objeto Statement usado para enviar consultas para o banco de dados
	     public Statement stmt = null;
	     public ResultSet r = null;
	     public Conexao() { }

	public Connection abreConexaoBD()
	{
	  try
	  {
	   try
	     {
	     Class.forName("com.mysql.jdbc.Driver");
	     }
	     catch (ClassNotFoundException ex)
	     {
	      return null;
	     }
	   try
	   {
	    // efetua conexao com o banco de dados
	    this.conexao = 
	          (Connection) DriverManager.getConnection(url,login,senha);
	   }
	   catch (SQLException ex)
	   {
	    return null;
	   }
	   return this.conexao;
	   }
	   catch (Exception e)
	   {
	    return null;
	   }
	}


}
