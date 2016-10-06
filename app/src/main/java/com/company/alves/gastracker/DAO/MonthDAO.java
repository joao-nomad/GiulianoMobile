package com.company.alves.gastracker.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.company.alves.gastracker.DataModel.DataModel;
import com.company.alves.gastracker.DataSource.DataSource;
import com.company.alves.gastracker.Model.Month;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cleiton on 08/09/2016.
 */
public class MonthDAO {
    DataSource ds;
    ContentValues values;
    public MonthDAO(Context context){
        ds = new DataSource(context);
    }

    //Cria um mes ano ou se passar o id do registro e a flag atualizar true atualiza o mesmo
    //Método para criar todos os meses do ano corrente, verificando se já nao existe antes.
    public boolean addMonth(Month mes){
        values = new ContentValues();
        values.put("id", mes.getId());
        values.put("name", mes.getName());
        values.put("number", mes.getNumber());
        values.put("year_id", mes.getIdYear());
        try {
            ds.persist(values, DataModel.getTbMonth());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //Buscar e retornar os Meses passando o id do ano
    public List<Month> getMonths(int idYear){
        Cursor cursor = ds.find(DataModel.getTbMonth(), null, "year_id = " + idYear, null, null, null, null, null);
        List<Month> lst = new ArrayList<Month>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                Month aux = new Month();
                aux.setId(cursor.getInt(cursor.getColumnIndex("id")));
                aux.setName(cursor.getString(cursor.getColumnIndex("name")));
                aux.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                aux.setNumber(cursor.getInt(cursor.getColumnIndex("year_id")));
                lst.add(aux);
                cursor.moveToNext();
            }
        }
        return lst;
    }

    //retorna o mes passando o id do mesmo
    public Month getMonthById(int idMotnh){
        Month retorno = new Month();
        Cursor cursor = ds.find(DataModel.getTbMonth(), null, "id = " + idMotnh, null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            retorno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            retorno.setName(cursor.getString(cursor.getColumnIndex("name")));
            retorno.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
            retorno.setNumber(cursor.getInt(cursor.getColumnIndex("year_id")));
        }
        return retorno;
    }
}