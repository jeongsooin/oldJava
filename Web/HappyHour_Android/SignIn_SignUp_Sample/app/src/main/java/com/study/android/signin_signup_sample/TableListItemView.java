package com.study.android.signin_signup_sample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.signin_signup_sample.databinding.TableItemViewBinding;

public class TableListItemView extends LinearLayout {

    public LinearLayout table_selectable;
    public TextView tvSelectableTable;
    public LayoutInflater inflater;
    public Boolean isSelectable = true;

    Drawable drawable1;
    Drawable drawable2;
    TableItemViewBinding binding;

    public TableListItemView(Context context) {
        super(context);
        drawable1 = ResourcesCompat.getDrawable(getResources(), R.drawable.textbackground4, null);
        drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.textbackground2, null);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.table_item_view, this, true);
        binding = DataBindingUtil.inflate(inflater, R.layout.table_item_view, null, false);
        tvSelectableTable = findViewById(R.id.tvSelectableTable);
        table_selectable = binding.tableSelectable;
    }

    public void setSelectableTableText(String tableText) {
        tvSelectableTable.setText(tableText);
    }

    public void setIsSelectable(Boolean isSelectable) { this.isSelectable = isSelectable; }

    public boolean getIsSelectable() { return this.isSelectable; }

    public void setTableDisable() {
        table_selectable.setBackground(drawable1);
    }

    public void setTableEnable() {
        table_selectable.setBackground(drawable2);
    }
}
