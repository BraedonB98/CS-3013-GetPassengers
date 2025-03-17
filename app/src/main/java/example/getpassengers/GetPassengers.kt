package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetPassengers : AppCompatActivity() {

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
    fun enterPassenger (v : View) {
        //setFriendInfo("Olaf", "Torgiersen", "720-878-2233")
        var textFirst = findViewById<EditText>(R.id.first_name)
        var textLast = findViewById<EditText>(R.id.last_name)
        var textPhone = findViewById<EditText>(R.id.phone_number)
        // get the string versions of numbers
        var firstName = textFirst.getText().toString()
        var lastName = textLast.getText().toString()
        var phoneNumber = textPhone.getText().toString()
        setFriendInfo(firstName, lastName, phoneNumber)
    }
    private fun backToMain(v : View) {
        Intent().let { friendInfoIntent ->
            friendInfoIntent.putExtra("first_name", fName)
            friendInfoIntent.putExtra("last_name", lName)
            friendInfoIntent.putExtra("phone_number", phoneNum)
            setResult(Activity.RESULT_OK, friendInfoIntent)
            finish()
        }
    }
}