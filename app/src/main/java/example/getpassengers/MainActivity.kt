package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val showListTextView: TextView
        get() = findViewById(R.id.show_list) // Ensure this ID matches your layout file

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        val data = activityResult.data
        val count = ((data?.getStringExtra("COUNT") ?: "")).toInt()
        showListTextView.text = ""
        for (i in 0 until count) {
            val passengerString = data?.getStringExtra("PASS$i") ?: ""
            showListTextView.append("$passengerString\n")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getList(v: View) {
        startForResult.launch(Intent(this, GetPassengers::class.java))
    }
}

data class Passenger(val lName: String, val fName: String, val phone: String) {
    override fun toString(): String {
        return "<<$fName $lName $phone>>"
    }
}