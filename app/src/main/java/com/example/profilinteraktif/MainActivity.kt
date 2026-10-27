package com.example.profilinteraktif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FormProfilScreen()
            }
        }
    }
}

@Composable
private fun FormProfilScreen() {
    var nama by rememberSaveable { mutableStateOf("") }
    var nim by rememberSaveable { mutableStateOf("") }
    var prodi by rememberSaveable { mutableStateOf("") }
    var sudahDisimpan by rememberSaveable { mutableStateOf(false) }

    val semuaValid = nama.isNotBlank() && nim.isNotBlank() && prodi.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Form Profil Mahasiswa",
            style = MaterialTheme.typography.headlineSmall
        )
        Text("Isi ketiga data di bawah. Kolom tidak boleh kosong atau hanya berisi spasi.")

        OutlinedTextField(
            value = nama,
            onValueChange = {
                nama = it
                sudahDisimpan = false
            },
            label = { Text("Nama") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nim,
            onValueChange = {
                nim = it
                sudahDisimpan = false
            },
            label = { Text("NIM") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = prodi,
            onValueChange = {
                prodi = it
                sudahDisimpan = false
            },
            label = { Text("Program Studi") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (!semuaValid) {
            Text(
                text = "Lengkapi seluruh kolom sebelum menyimpan.",
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                // Pemeriksaan ulang dilakukan juga di event tombol sebagai pengaman.
                if (semuaValid) {
                    nama = nama.trim()
                    nim = nim.trim()
                    prodi = prodi.trim()
                    sudahDisimpan = true
                }
            },
            enabled = semuaValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Profil")
        }

        if (sudahDisimpan) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("Profil Tersimpan", style = MaterialTheme.typography.titleMedium)
                    Text("Nama: $nama")
                    Text("NIM: $nim")
                    Text("Program Studi: $prodi")
                }
            }
        }
    }
}