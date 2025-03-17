package example.getpassengers

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val linearLayout: LinearLayout
        get() = findViewById(R.id.show_list)
    var flag = false
    var idCount = 0
    private val startForResult = //When returning from GetPassengers
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val fName = data?.getStringExtra("first_name") ?: ""
            val lName = data?.getStringExtra("last_name") ?: ""
            val phoneNumber = data?.getStringExtra("phone_number") ?: ""
            addFriendView(Friend(lName, fName, phoneNumber))
        }
    override fun onCreate(savedInstanceState: Bundle?) { //Main on create
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun getList(v : View) { //Launch GetPassengers
        startForResult.launch(Intent(this,
            GetPassengers::class.java))
    }
    fun addPassengerView(f: Friend) {
        flag = !flag
        val textView = TextView(this)
        textView.text = f.toString()
        textView.setId(idCount++)
        if(flag) textView.setBackgroundColor(Color.rgb(255, 255, 100))
        else textView.setBackgroundColor(Color.rgb(100, 255, 100))

        textView.textSize = 14F
        textView.setTextColor(Color.BLUE)
        linearLayout.addView(textView)
    }
}
class Passenger(val lName:String, val fName: String, val phone: String) {
    override fun toString () : String {
        var s = "<<" + this.fName + " " + this.lName + " " + this.phone + ">>"
        return s

    }
}