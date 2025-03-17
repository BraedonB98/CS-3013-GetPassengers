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

    private val passengers = mutableListOf<Passenger>()

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        val data = activityResult.data
        val fName = data?.getStringExtra("first_name") ?: ""
        val lName = data?.getStringExtra("last_name") ?: ""
        val phoneNumber = data?.getStringExtra("phone_number") ?: ""
        val newPassenger = Passenger(lName, fName, phoneNumber)
        passengers.add(newPassenger)
        addPassengerToTextView(newPassenger)
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

    private fun addPassengerToTextView(passenger: Passenger) {
        showListTextView.append("${passenger}\n")
    }
}

data class Passenger(val lName: String, val fName: String, val phone: String) {
    override fun toString(): String {
        return "<<$fName $lName $phone>>"
    }
}