package com.hojennifer.votefood;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class VoteDialog extends AppCompatDialogFragment {
    ImageView pic;
    TextView name;
    NumberPicker rater;
    Button confirm;
    Button cancel;
    Food food;
    VoteDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = this;

        food = (Food) getArguments().getSerializable("food info");

        name = view.findViewById(R.id.tv_foodname);
        name.setText( food.name );
        pic = view.findViewById(R.id.iv_picture);
        pic.setImageResource(food.drawableID);
        rater = view.findViewById(R.id.number_picker);
        rater.setMinValue(1);
        rater.setMaxValue(10);
        rater.setValue(food.votes);
        cancel = view.findViewById(R.id.b_cancel);
        confirm = view.findViewById(R.id.b_confirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = rater.getValue();
                food.votes = score;
                dialog.dismiss();
            }
        });
    }
}
