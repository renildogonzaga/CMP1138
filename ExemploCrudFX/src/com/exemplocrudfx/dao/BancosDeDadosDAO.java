package com.exemplocrudfx.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.exemplocrudfx.model.BancosDeDados;

public class BancosDeDadosDAO {

	public Conexao conexao = new Conexao();
    
    
    public ArrayList<BancosDeDados> listaconfbdAll() throws SQLException
    {
        Connection conn;
    	BancosDeDados a;
        ArrayList<BancosDeDados> v = new ArrayList<BancosDeDados>();
        conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = conn.createStatement();
        rs= st.executeQuery("select * from bancos_dados order by bdd_nomebd");
       while (rs.next())
       {
           a = new BancosDeDados();
           a.setBdd_id(rs.getInt("bdd_id"));
           a.setBdd_servidor(rs.getString("bdd_servidor"));
           a.setTip_id(rs.getInt("tip_id"));
           a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
           a.setBdd_nomebd(rs.getString("bdd_nomebd"));
           a.setBdd_observacao(rs.getString("bdd_observacao"));
           a.setBdd_senha(rs.getString("bdd_senha"));
           a.setBdd_usuario(rs.getString("bdd_usuario"));
           v.add(a);
       }
        return v;
    }

    public ArrayList<BancosDeDados> buscaBancosDeDadosPorTipoBancoDados(int tipo) throws SQLException
    {
        Connection conn;
    	BancosDeDados a;
        ArrayList<BancosDeDados> v = new ArrayList<BancosDeDados>();
        conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = conn.createStatement();
        String sql="select * from bancos_dados where tip_id="+tipo;
        rs= st.executeQuery(sql);
       while (rs.next())
       {
           a = new BancosDeDados();
           a.setBdd_id(rs.getInt("bdd_id"));
           a.setBdd_servidor(rs.getString("bdd_servidor"));
           a.setTip_id(rs.getInt("tip_id"));
           a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
           a.setBdd_nomebd(rs.getString("bdd_nomebd"));
           a.setBdd_observacao(rs.getString("bdd_observacao"));
           a.setBdd_senha(rs.getString("bdd_senha"));
           a.setBdd_usuario(rs.getString("bdd_usuario"));
           v.add(a);
       }
        return v;
    }

    
    
    public ArrayList<BancosDeDados> buscaconfbdPorDescricaoNomeBancoAll(String descricao)
            throws SQLException
    {
    	BancosDeDados a;
        String sql=null;
        if (descricao.equals("") || descricao.length()==0 || descricao.isEmpty())
        {
            sql="select * from bancos_dados order by bdd_nomebd"; 
        }
        else
        {
        	sql="select * from bancos_dados where bdd_nomebd like '";
            sql+=descricao+"%' order by bdd_nomebd"; //descricao = servidor
        }    
        ArrayList<BancosDeDados> v = new ArrayList<BancosDeDados>();
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = null;
        st = conn.createStatement();
        rs= st.executeQuery(sql);
       while (rs.next())
       {
           a = new BancosDeDados();
           a.setBdd_id(rs.getInt("bdd_id"));
           a.setBdd_servidor(rs.getString("bdd_servidor"));
           a.setTip_id(rs.getInt("tip_id"));
           a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
           a.setBdd_nomebd(rs.getString("bdd_nomebd"));
           a.setBdd_observacao(rs.getString("bdd_observacao"));
           a.setBdd_senha(rs.getString("bdd_senha"));
           a.setBdd_usuario(rs.getString("bdd_usuario"));
           v.add(a);
       }
        return v;
    }

       public BancosDeDados buscaconfbdPorDescricao(String descricao)
            throws SQLException
    {
    	   BancosDeDados a= new BancosDeDados();
        String sql=null;
        sql="select * from bancos_dados where bdd_servidor like '";
        sql+=descricao+"%' order by bdd_servidor"; //descricao=servidor
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = conn.createStatement();
        rs= st.executeQuery(sql);
       if (rs.next())
       {
           a.setBdd_id(rs.getInt("bdd_id"));
           a.setBdd_servidor(rs.getString("bdd_servidor"));
           a.setTip_id(rs.getInt("tip_id"));
           a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
           a.setBdd_nomebd(rs.getString("bdd_nomebd"));
           a.setBdd_observacao(rs.getString("bdd_observacao"));
           a.setBdd_senha(rs.getString("bdd_senha"));
           a.setBdd_usuario(rs.getString("bdd_usuario"));
       }
       else
    	   a=null;
        return a;
    }
    

       public BancosDeDados buscaconfbdPorNomeBanco(String descricao)
            throws SQLException
    {
    	   BancosDeDados a= new BancosDeDados();
        String sql=null;
        sql="select bancos_dados.bdd_id, bancos_dados.tip_id,bancos_dados.bdd_servidor, bancos_dados.bdd_nomebd,";
        sql+=" bancos_dados.bdd_usuario, bancos_dados.bdd_senha where bancos_dados.bdd_nomebd like '"+descricao+"%'";
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = conn.createStatement();
        rs= st.executeQuery(sql);
       if (rs.next())
       {
           a.setBdd_id(rs.getInt("bdd_id"));
           a.setBdd_servidor(rs.getString("bdd_servidor"));
           a.setTip_id(rs.getInt("tip_id"));
          // a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
           a.setBdd_nomebd(rs.getString("bdd_nomebd"));
          // a.setBdd_observacao(rs.getString("bdd_observacao"));
           a.setBdd_senha(rs.getString("bdd_senha"));
           a.setBdd_usuario(rs.getString("bdd_usuario"));
           a.setBdd_nomeTipoBD(rs.getString("tip_sigla"));
       }
       else
    	   a=null;
        return a;
    }

       public BancosDeDados buscaDadosBDMaisNomeTipoDB(String descricao)
               throws SQLException
       {
       	   BancosDeDados a= new BancosDeDados();
           String sql=null;
           sql="select b.bdd_id, b.bdd_servidor, b.tip_id,b.bdd_customizar_campos,";
           sql+="b.bdd_nomebd, b.bdd_observacao,b.bdd_senha,b.bdd_usuario";
           sql+=" from bancos_dados b  where b.bdd_nomebd='"+descricao+"'";
           Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
          if (rs.next())
          {
              a.setBdd_id(rs.getInt("bdd_id"));
              a.setBdd_servidor(rs.getString("bdd_servidor"));
              a.setTip_id(rs.getInt("tip_id"));
              a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
              a.setBdd_nomebd(rs.getString("bdd_nomebd"));
              a.setBdd_observacao(rs.getString("bdd_observacao"));
              a.setBdd_senha(rs.getString("bdd_senha"));
              a.setBdd_usuario(rs.getString("bdd_usuario"));
              a.setBdd_nomeTipoBD(rs.getString("tip_sigla"));
           }
          else
       	   a=null;
           return a;
       }
   
       
       
    public BancosDeDados buscaconfbdPorId(int codigo)
            throws SQLException
    {
	   BancosDeDados a = new BancosDeDados();
        String sql=null;
        sql="select bancos_dados.bdd_id,bancos_dados.tip_id,bancos_dados.bdd_servidor,bancos_dados.bdd_nomebd,bancos_dados.bdd_usuario,";
        sql+="bancos_dados.bdd_senha,bancos_dados.bdd_observacao from bancos_dados where bdd_id = "+codigo;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = null;
        st = conn.createStatement();
        rs= st.executeQuery(sql);
        if (rs.next())
        {
            a.setBdd_id(rs.getInt("bdd_id"));
            a.setBdd_servidor(rs.getString("bdd_servidor"));
            a.setTip_id(rs.getInt("tip_id"));
            //a.setBdd_customizar_campos(rs.getString("bdd_customizar_campos"));
            a.setBdd_nomebd(rs.getString("bdd_nomebd"));
            //a.setBdd_observacao(rs.getString("bdd_observacao"));
            a.setBdd_senha(rs.getString("bdd_senha"));
            a.setBdd_usuario(rs.getString("bdd_usuario"));
            a.setBdd_nomeTipoBD(rs.getString("tip_sigla"));
           
          }
        else
     	   a=null;
        return a;
    }
   
   public String buscaStringPorId(int codigo)
           throws SQLException
   {
	   BancosDeDados a = null;
       String sql=null;
       sql="select * from confbd where tip_id = "+codigo;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs = null;
       Statement st = conn.createStatement();
       rs= st.executeQuery(sql);
       String s="";
       if (rs.next())
       {
           s=rs.getString("bdd_nomebd");
       }
       else
    	   s="";
       return s;
   }
    
    public int incluiconfbd(BancosDeDados c) throws SQLException 
    {
        //retorna 0=> erro na inclusão
        //        1=> incluiu
        //        2=> registro cadastrado
        int retorno=0;
        String sql=null;
        Connection conn= conexao.abreConexaoBD();
        BancosDeDadosDAO bdd = new BancosDeDadosDAO();
        ResultSet rs;
        Statement st = conn.createStatement();
        String desc=c.getBdd_nomebd().trim();
        sql="select * from bancos_dados where bdd_nomebd ='"+desc+"'";
        rs = st.executeQuery(sql);
        if (rs.next()==false)
            retorno=0;
        else
            retorno=2; // possui cadastro
        if (retorno==0)
        {
            sql="insert into bancos_dados (tip_id,bdd_servidor,bdd_nomebd,bdd_usuario,";
            sql+="bdd_senha,bdd_observacao,bdd_customizar_campos)";
            sql+=" values (";
            sql+=""+c.getTip_id()+",'"+c.getBdd_servidor()+"','"+c.getBdd_nomebd()+"','";
            sql+=c.getBdd_usuario()+"','"+c.getBdd_senha()+"','"+c.getBdd_observacao()+"','";
            sql+=c.getBdd_customizar_campos()+"')";

            int qRs = 0;
			try {
				qRs = st.executeUpdate(sql);
			} catch (SQLException e) {
				retorno=0;
			}
            if (qRs==0)
                retorno=0;
            else
                retorno=1;
            
        }
        return retorno;
    }

    public boolean excluiconfbd(BancosDeDados c) throws SQLException
    {
        //retorna  true => exclusão ok
        //retorna  false=> erro de exclusão
        boolean retorno=false;
        String sql=null;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs=null;
        Statement st = null;
        st=conn.createStatement();
        sql="delete from bancos_dados where bdd_id ="+c.getBdd_id();
        int qRs = st.executeUpdate(sql);
        if (qRs !=0)
            retorno=true;
        else
            retorno=false;

        return retorno;
    }

    public boolean alteraconfbd(BancosDeDados c) throws SQLException
    {
        //retorna  true => exclusão ok
        //retorna  false=> erro de exclusão
        boolean retorno=false;
        String sql=null;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs=null;
        Statement st=null;
        st = conn.createStatement();
        sql="update bancos_dados set bdd_servidor='"+c.getBdd_servidor()+"',";
        sql+="tip_id="+c.getTip_id()+","+"bdd_nomebd='"+c.getBdd_nomebd()+"',";
        sql+="bdd_usuario='"+c.getBdd_usuario()+"',"+"bdd_senha='"+c.getBdd_senha()+"',";
        sql+="bdd_observacao='"+c.getBdd_observacao()+"',"+"bdd_customizar_campos='"+c.getBdd_customizar_campos()+"'";
        		
        sql+=" where bdd_id="+c.getBdd_id();
        int qRs = st.executeUpdate(sql);
        if (qRs !=0)
            retorno=true;
        else
            retorno=false;

        return retorno;
    }
	
}
