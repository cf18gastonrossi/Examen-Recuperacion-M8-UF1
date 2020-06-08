package com.example.recuperacion.ui.ver_reservas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recuperacion.Model.Reserva;

import java.util.ArrayList;

public class VerReservasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VerReservasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}