package cubex.mahesh.sqlitedbtest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var et1:EditText? = null
    var et2:EditText? = null
    var et3:EditText? = null
    var et4:EditText? = null
    var dBase:SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et1= findViewById(R.id.et1)
        et2= findViewById(R.id.et2)
        et3= findViewById(R.id.et3)
        et4= findViewById(R.id.et4)
        dBase = openOrCreateDatabase("empdb",
                Context.MODE_PRIVATE,null)
        dBase?.execSQL("create table if not exists employee(id number primary key,name varchar(100),desig varchar(100),dept varchar(100))")
    }
    fun  insert(v:View){
     //   String table, String nullColumnHack, ContentValues values
        var cv = ContentValues( )
        cv.put("id",et1?.text.toString().toInt())
        cv.put("name",et2?.text.toString())
        cv.put("desig",et3?.text.toString())
        cv.put("dept",et4?.text.toString())

     var status =    dBase?.insert("employee",
                null,cv)
        if(status == -1.toLong()){
            Toast.makeText(this,
   "Failed to Insert",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,
  "Successfully  Inserted",Toast.LENGTH_LONG).show()
            et1?.setText(""); et2?.setText("");
            et3?.setText("");et4?.setText("")
        }

    }
    fun  read(v:View){
/* String table, String[] columns, String selection,
            String[] selectionArgs, String groupBy,
            String having, String orderBy*/

   var c = dBase?.query("employee",
                    null,
                "id=?",
           arrayOf(et1?.text.toString()),
                null,null,null)
        var list= ArrayList<String>( )
    while(c!!.moveToNext()){
                list.add("${c.getInt(0)} |  " +
                        "${c.getString(1)} | "+
                        "${c.getString(2)} |"+
                        "${c.getString(3)}")
    }
        var adapter = ArrayAdapter<String>(
this,android.R.layout.simple_spinner_item,
                list)
        lview.adapter = adapter
    }
    fun  update(v:View){

    }
    fun  delete(v:View){

    }
}
