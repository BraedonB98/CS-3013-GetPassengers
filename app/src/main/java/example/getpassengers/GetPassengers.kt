package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetPassengers : AppCompatActivity() {
    var passengerList = mutableListOf<Passenger>()
    private val accumListTextView: TextView
        get() = findViewById(R.id.accum_list) // Ensure this ID matches your layout file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_passengers)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public fun enterPassenger(v: View) {
        val textFirst = findViewById<EditText>(R.id.first_name)
        val textLast = findViewById<EditText>(R.id.last_name)
        val textPhone = findViewById<EditText>(R.id.phone_number)
        val firstName = textFirst.text.toString()
        val lastName = textLast.text.toString()
        val phoneNumber = textPhone.text.toString()
        val newPassenger = Passenger(lastName, firstName, phoneNumber)
        passengerList.add(newPassenger)
        accumListTextView.append("\n${newPassenger}")
    }

    public fun backToMain(v: View) {
        Intent().let { passengerInfoIntent ->
            passengerInfoIntent.putExtra("COUNT", passengerList.size.toString())
            for (i in passengerList.indices) {
                passengerInfoIntent.putExtra("PASS$i", passengerList[i].toString())
            }
            setResult(Activity.RESULT_OK, passengerInfoIntent)
            finish()
        }
    }
}