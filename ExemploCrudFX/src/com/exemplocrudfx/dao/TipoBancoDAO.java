package com.exemplocrudfx.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.exemplocrudfx.model.BancosDeDados;
import com.exemplocrudfx.model.TipoBD;

public class TipoBancoDAO {
	
	public Conexao conexao = new Conexao();
    
    
    public ArrayList<TipoBD> listaTipoBDAll() throws SQLException
    {
        Connection conn;
    	TipoBD a;
        ArrayList<TipoBD> v = new ArrayList<TipoBD>();
        conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = null;
        st = conn.createStatement();
        rs= st.executeQuery("select * from tipos_bd order by tip_sigla");
       while (rs.next())
       {
           a = new TipoBD();
           a.setD36_tipoBd_id(rs.getInt("tip_id"));
           a.setD36_tipoBd_sigla(rs.getString("tip_sigla"));
           v.add(a);
       }
        return v;
    }

    public ArrayList<TipoBD> buscaTipoBDPorDescricaoAll(String descricao)
            throws SQLException
    {
        TipoBD a;
        String sql=null;
        sql="select * from tipos_bd where tip_sigla like '";
        sql+=descricao+"%' order by tip_sigla"; //descricao = sigla
        ArrayList<TipoBD> v = new ArrayList<TipoBD>();
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = null;
        st = conn.createStatement();
        rs= st.executeQuery(sql);
       while (rs.next())
       {
           a = new TipoBD();
           a = new TipoBD();
           a.setD36_tipoBd_id(rs.getInt("tip_id"));
           a.setD36_tipoBd_sigla(rs.getString("tip_sigla"));
   
           v.add(a);
       }
        return v;
    }

       public TipoBD buscaTipoBDPorDescricao(String descricao)
            throws SQLException
    {
        TipoBD a= new TipoBD();
        String sql=null;
        sql="select * from tipos_bd where tip_sigla like '";
        sql+=descricao+"%' order by tip_sigla"; //descricao=sigla
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = conn.createStatement();
        rs= st.executeQuery(sql);
       if (rs.next())
       {
           a.setD36_tipoBd_id(rs.getInt("tip_id"));
           a.setD36_tipoBd_sigla(rs.getString("tip_sigla"));
          }
       else
    	   a=null;
        return a;
    }
    
   public TipoBD buscaTipoBDPorId(int codigo)
            throws SQLException
    {
        TipoBD a = new TipoBD();
        String sql=null;
        sql="select * from tipos_bd where tip_id = "+codigo;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st=null;
        st = conn.createStatement();
        rs= st.executeQuery(sql);
        if (rs.next())
        {
            a = new TipoBD();
            a.setD36_tipoBd_id(rs.getInt("tip_id"));
            a.setD36_tipoBd_sigla(rs.getString("tip_sigla"));
            }
        else
     	   a=null;
        return a;
    }
    
    public int incluiTipoBD(TipoBD c) throws SQLException
    {
        //retorna 0=> erro na inclusão
        //        1=> incluiu
        //        2=> registro cadastrado
        int retorno=0;
        String sql=null;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs;
        Statement st = conn.createStatement();
        String desc=c.getD36_tipoBd_sigla().trim();
        sql="select * from tipos_bd where tip_sigla ='"+desc+"'";
        rs = st.executeQuery(sql);
        if (rs.next()==false)
            retorno=0;
        else
            retorno=2; // possui cadastro
        if (retorno==0)
        {
            sql="insert into tipos_bd (tip_sigla) values (";
            sql+="'"+c.getD36_tipoBd_sigla()+"')";
            int qRs= st.executeUpdate(sql);
            if (qRs==0)
                retorno=0;
            else
                retorno=1;
        }
        return retorno;
    }

    public boolean excluiTipoBD(TipoBD c) throws SQLException
    {
        //retorna  true => exclusão ok
        //retorna  false=> erro de exclusão
        boolean retorno=false;
        String sql=null;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs=null;
        Statement st = null;
        st=conn.createStatement();
        sql="delete from tipos_bd where tip_id ="+c.getD36_tipoBd_id();
        int qRs = st.executeUpdate(sql);
        if (qRs !=0)
            retorno=true;
        else
            retorno=false;

        return retorno;
    }

    public boolean alteraTipoBD(TipoBD c) throws SQLException
    {
        //retorna  true => exclusão ok
        //retorna  false=> erro de exclusão
        boolean retorno=false;
        String sql=null;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs=null;
        Statement st=null;
        st = conn.createStatement();
        sql="update tipos_bd set tip_sigla='"+c.getD36_tipoBd_sigla()+"'";
        sql+=" where tip_id="+c.getD36_tipoBd_id();
        int qRs = st.executeUpdate(sql);
        if (qRs !=0)
            retorno=true;
        else
            retorno=false;

        return retorno;
    }

    public String buscaStringPorId(int codigo)
            throws SQLException
    {
 	   BancosDeDados a = null;
        String sql=null;
        sql="select * from confbd where d36_tipo_id = "+codigo;
        Connection conn= conexao.abreConexaoBD();
        ResultSet rs = null;
        Statement st = conn.createStatement();
        rs= st.executeQuery(sql);
        String s="";
        if (rs.next())
        {
            s=rs.getString("tip_sigla");
        }
        else
     	   s="";
        return s;
    }


}
