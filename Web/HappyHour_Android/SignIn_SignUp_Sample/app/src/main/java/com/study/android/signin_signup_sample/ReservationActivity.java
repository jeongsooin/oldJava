package com.study.android.signin_signup_sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.study.android.signin_signup_sample.databinding.ActivityReservationBinding;
import com.study.android.signin_signup_sample.databinding.DialogMaterialCalenderBinding;
import com.study.android.signin_signup_sample.databinding.DialogMenuBinding;
import com.study.android.signin_signup_sample.databinding.DialogTableBinding;
import com.transferwise.sequencelayout.SequenceStep;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "ReservationActivity";
    public static ReservationActivity reservationActivity;
    public static ReservationDTO reservationDTO = ReservationDTO.getInstance();
    public static ManageRsvDTO manageRsvDTO = new ManageRsvDTO();

    private Button buttonPickDate;
    private Button buttonPickTime;
    private Button buttonSetPersonNum;
    private Button buttonPickTable;
    private Button buttonPickMenu;
    private Button buttonRsvConfirm;

    SequenceStep sequenceStep1;
    SequenceStep sequenceStep2;
    SequenceStep sequenceStep3;
    SequenceStep sequenceStep4;
    SequenceStep sequenceStep5;
    SequenceStep sequenceStep6;

    int year, month, day;
    int maxTableNumber = 10;
    int selectableTableNum = 1;

    String rDate = "";
    String mDate = "";
    String sDate = "";
    String rType = "일반";
    String openTime = "09:00";
    String closeTime = "23:00";
    String resultString;

    HashMap<String, Integer> timesMap = new HashMap<>();
    HashMap<Integer, Boolean> tablesMap = new HashMap<>();
    HashMap<String, List<Integer>> tableTimeUsage = new HashMap<>();


    String menuName = "하우스샐러드";
    String menuDescription = "식사를 위한 작은 샐러드";
    String menuPrice = "3500";
    int menuPriceint = 3500;

    String menuName1 = "크림치즈스파게티";
    String menuDescription1 = "부드러운 크림소스와 이탈리아 치즈와의 환상의 궁합";
    String menuPrice1 = "9000";
    int menuPrice1int = 9000;

    String menuName2 = "치즈볶음밥";
    String menuDescription2 = "신선한 해물에 천연재료를 사용해 볶아낸 요리";
    String menuPrice2 = "8300";
    int menuPrice2int = 8300;

    String menuName3 = "치즈그라탕";
    String menuDescription3 = "야채로 조리한 밥위에 모짜렐라 치즈를 듬뿍 얹어 구워낸 오븐요리";
    String menuPrice3 = "9000";
    int menuPrice3int = 9000;

    String menuName4 = "토마토그라탕";
    String menuDescription4 = "야채로 조리한 밥위에 토마토를 얹어 구워낸 오븐요리";
    String menuPrice4 = "9000";
    int menuPrice4int = 9000;

    int menuPriceSum = 0;

    String[] availableTimes;
    String[] rsvTimes = { "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                          "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
                          "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                          "21:00", "21:30", "22:00", "22:30", "23:00" };

    List<Integer> rsvTables = new ArrayList<>();
    boolean[] rsvTablesUsages;
    List<Integer> tableList = new ArrayList<>();
    List<MenuItem> menuDTOList = new ArrayList<>();

    ListView menuList;
    GridView tableGrid;
    DialogTableBinding tableBinding;
    TableItemAdapter tableItemAdapter;
    MaterialCalendarView materialCalendarView;
    DialogMaterialCalenderBinding binding;
    ActivityReservationBinding activityReservationBinding;
    DialogMenuBinding dialogMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReservationBinding = DataBindingUtil.setContentView(this, R.layout.activity_reservation);

        LayoutInflater inflater = getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_material_calender, null, false);
        reservationDTO.setRType(rType);
        reservationDTO.setRName(MainActivity.memberDTO.getName());
        tableItemAdapter = new TableItemAdapter(getApplicationContext());
        tableBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_table, null, false);
        dialogMenuBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_menu, null, false);
        menuList = dialogMenuBinding.menuList;
        tableGrid = tableBinding.tableGrid;



        if (tableGrid.getParent() != null) { ((ViewGroup)tableGrid.getParent()).removeView(tableGrid); }
        tableGrid.setAdapter(tableItemAdapter);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

//        for (int i = 1; i < 9; i++) {
//            setMenuOptions(i);
//        }

        sequenceStep1 = activityReservationBinding.sequenceStep1;
        sequenceStep2 = activityReservationBinding.sequenceStep2;
        sequenceStep3 = activityReservationBinding.sequenceStep3;
        sequenceStep4 = activityReservationBinding.sequenceStep4;
        sequenceStep5 = activityReservationBinding.sequenceStep5;
        sequenceStep6 = activityReservationBinding.sequenceStep6;

        buttonPickTime = activityReservationBinding.buttonPickTime;
        buttonPickTime.setEnabled(false);
        buttonPickDate = activityReservationBinding.buttonPickDate;
        buttonPickDate.setEnabled(true);
        buttonSetPersonNum = activityReservationBinding.buttonSetPersonNum;
        buttonSetPersonNum.setEnabled(false);
        buttonPickTable = activityReservationBinding.buttonPickTable;
        buttonPickTable.setEnabled(false);
        buttonPickMenu = activityReservationBinding.buttonPrickMenu;
        buttonPickMenu.setEnabled(false);
        buttonRsvConfirm = activityReservationBinding.buttonRsvConfirm;
        buttonRsvConfirm.setEnabled(false);

        buttonPickTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { displayTableSelectionDialog();
            }
        });
        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calenderDialogShow();
            }
        });
        buttonPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTimeSelectionDialog();
            }
        });
        buttonSetPersonNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPersonNumberSelectionDialog();
            }
        });

        buttonPickMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMenuSelectionDialog();
            }
        });

        buttonRsvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayConfirmAlert();
            }
        });
    }

    public void createReservationOptionsMap(int maxTableNumbers) {

        rsvTablesUsages = new boolean[maxTableNumbers];

        for (int i = 0; i < maxTableNumbers; i++) {
            rsvTables.add(i + 1);
            rsvTablesUsages[i] = true;
        }

        for (int i = 0; i < rsvTimes.length; i++) {
            timesMap.put(rsvTimes[i], i);
        }

        for (int i = 0; i < rsvTables.size(); i++) {
            tablesMap.put(rsvTables.get(i), rsvTablesUsages[i]);
        }

    }

    public void createTableTimeUsageMap() {
        for (int i = 0; i <availableTimes.length; i++) {
            tableTimeUsage.put(availableTimes[i], rsvTables);
        }
    }

    public void disableTableOption(String time, Integer tableNumber) {

        List<Integer> availableTables = tableTimeUsage.get(time);
        availableTables.remove(tableNumber);
        tableTimeUsage.remove(time);
        tableTimeUsage.put(time, availableTables);

    }

    public void createTableOption(String time) {
        if (!tableItemAdapter.items.isEmpty()) {
            tableItemAdapter.items.clear();
        }

        if (!tableList.isEmpty()) {
            tableList.clear();
        }

        List<Integer> availableTables = tableTimeUsage.get(time);

        for (int i = 0; i < availableTables.size(); i++) {
            tableList.add(availableTables.get(i));
            tableItemAdapter.addItem(new TableItem(i + 1));
        }
    }

    public void createTimeOptions(String openTime, String closeTime) {
        int start = timesMap.get(openTime);
        int end = timesMap.get(closeTime);
        int length = end - start + 1;

        availableTimes = new String[length];

        for (int i = 0; i < length; i++) {
            availableTimes[i] = rsvTimes[i + start];
        }
    }

    public void setMenuOptions(int mid) {

        MenuItem menuDTO = new MenuItem();

        try {
            String sendInfo = "getMenuOptions";

            String sendMsg = "mid=" + mid;

            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");
                menuDTO.setMid(Integer.parseInt(splitResult[1]));
                menuDTO.setMenu_name(splitResult[2]);
                menuDTO.setMenu_description(splitResult[3]);
                menuDTO.setMenu_price(Integer.parseInt(splitResult[4]));
                menuDTO.setMenu_imagename(splitResult[5]);
                menuDTO.setMenu_extension(splitResult[6]);
                menuDTO.setMenu_code(Integer.parseInt(splitResult[7]));
                menuDTO.setMenu_qty(Integer.parseInt(splitResult[8]));
                menuDTO.setMwriter(splitResult[9]);

                menuDTOList.add(menuDTO);
                Log.d(TAG, "MENU ADDED");
            } else {
                Log.d(TAG, "No Selectable Menu");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReservationOptions(String date) {
        try {
            String sendInfo = "getReservationOptions";
            String sendMsg = "date=" + date;
            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");
                mDate = splitResult[1];
                maxTableNumber = Integer.parseInt(splitResult[2]);
                openTime = splitResult[3];
                closeTime = splitResult[4];

                manageRsvDTO.setMdate(mDate);
                manageRsvDTO.setMtable(maxTableNumber);
                manageRsvDTO.setOpentime(openTime);
                manageRsvDTO.setClosetime(closeTime);

                createReservationOptionsMap(maxTableNumber);
                createTimeOptions(openTime,closeTime);
                createTableTimeUsageMap();
            } else {

                Log.d(TAG, "Reservation Setting will be default.");
                createReservationOptionsMap(maxTableNumber);
                createTimeOptions(openTime,closeTime);
                createTableTimeUsageMap();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calenderDialogShow() {

        materialCalendarView = binding.calendarView;
        if (materialCalendarView.getParent() != null) {
            ((ViewGroup)materialCalendarView.getParent()).removeView(materialCalendarView);
        }

        materialCalendarView.state().edit()
                            .setFirstDayOfWeek(Calendar.SUNDAY)
                            .setMinimumDate(CalendarDay.from(year, month, day))
                            .setMaximumDate(CalendarDay.from(year, month + 1, day - 1))
                            .setCalendarDisplayMode(CalendarMode.MONTHS)
                            .commit();
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        materialCalendarView.addDecorators(new SaturdayDecorator(), new SundayDecorator());
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget,
                                       @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDay();

                String pickedDate = year + " 년 " + month + " 월 " + day +" 일";
                rDate = year +"-" + "0" + month + "-" + day;
                sDate = year + "/" + month + "/" + day;
                Toast.makeText(ReservationActivity.this, pickedDate, Toast.LENGTH_SHORT).show();
                sequenceStep1.setTitle(pickedDate);
                sequenceStep1.setActive(true);
                reservationDTO.setRDate(rDate);
                setReservationOptions(rDate);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
        builder.setView(materialCalendarView);
        builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                buttonPickTime.setEnabled(true);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (buttonPickTime.isEnabled())
                    buttonPickTime.setEnabled(false);
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displayPersonNumberSelectionDialog() {
        final List<Integer> ListItems = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            ListItems.add(i);
        }

        final CharSequence[] items = new CharSequence[ListItems.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = (i + 1) + " 명";
        }
        final List<Integer> SelectedItems = new ArrayList<>();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);

        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
        builder.setTitle("예약 인원을 선택하세요");
        builder.setSingleChoiceItems(items, defaultItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SelectedItems.clear();
                SelectedItems.add(which);
            }
        });

        builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedNum = 0;

                if (!SelectedItems.isEmpty()) {
                    int index = (int) SelectedItems.get(0);
                    selectedNum = ListItems.get(index) + 1;
                    if (selectedNum <= 4) {
                        selectableTableNum = 1;
                    } else if (selectedNum > 4 && selectedNum <= 8) {
                        selectableTableNum = 2;
                    } else if (selectedNum > 8 && selectedNum <= 13) {
                        selectableTableNum = 3;
                    }
                }
                Toast.makeText(ReservationActivity.this, "selectableTableNum : " + selectableTableNum, Toast.LENGTH_SHORT).show();
                reservationDTO.setRNum(selectedNum);
                sequenceStep3.setTitle(selectedNum + " 명");
                sequenceStep2.setActive(false);
                sequenceStep3.setActive(true);
                buttonPickTable.setEnabled(true);

            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
    private void displayTimeSelectionDialog() {
        final List<String> ListItems = new ArrayList<>();
        for (int i=0; i < availableTimes.length; i++) {
            ListItems.add(availableTimes[i]);
        }
        final CharSequence[] items =  ListItems.toArray(new String[ListItems.size()]);
        final List SelectedItems  = new ArrayList();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);

        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
        builder.setTitle("예약하고자 하는 시간을 선택하세요");
        builder.setSingleChoiceItems(
                items, defaultItem,
                (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectedItems.clear();
                        SelectedItems.add(which);
                    }
                }));
        builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedTime = "";

                if (!SelectedItems.isEmpty()) {
                    int index = (int) SelectedItems.get(0);
                    selectedTime = ListItems.get(index);
                }
                reservationDTO.setRTime(selectedTime);
                sequenceStep2.setTitle(selectedTime);
                sequenceStep1.setActive(false);
                sequenceStep2.setActive(true);
                buttonSetPersonNum.setEnabled(true);
            }
        });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void displayMenuSelectionDialog() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add(menuName + "     "+ menuPrice + " 원");
        ListItems.add(menuName1 + "     "+ menuPrice1 + " 원");
        ListItems.add(menuName2 + "     "+ menuPrice2 + " 원");
        ListItems.add(menuName3 + "     "+ menuPrice3 + " 원");
        ListItems.add(menuName4 + "     "+ menuPrice4 + " 원");

        final CharSequence[] items =  ListItems.toArray(new String[ListItems.size()]);

        final boolean[] checkedItems = {false, false, false, false, false};
        final Integer[] prices = {menuPriceint, menuPrice1int, menuPrice2int, menuPrice3int, menuPrice4int};

        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
        builder.setTitle("메뉴를 선택하세요");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            int priceSum = 0;
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                menuPriceSum = menuPriceSum + prices[which];
            }
        });

        builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String price = menuPriceSum+"";
                reservationDTO.setRPayment(price);
                sequenceStep5.setTitle(price + " 원");
                buttonRsvConfirm.setEnabled(true);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private void insertReservationData(ReservationDTO reservationDTO) {
        try {
            String sendInfo = "insertReservationData";

            String sendMsg = "rname=" + "nepec1047@naver.com" + "&rtype=" + reservationDTO.getRType() + "&rpayment=" + reservationDTO.getRPayment() + "&rnum=" + reservationDTO.getRNum() + "&rtable=" + reservationDTO.getRTable() + "&rdate=" + reservationDTO.getRDate() + "&rtime=" + reservationDTO.getRTime();

            resultString = new ConnectDB_JSP(sendInfo).execute(sendMsg).get();

            if (resultString != null) {
                String[] splitResult = resultString.split(" ");

                if (splitResult[0].contains("SUCCESS")) {
                    Log.i("Reservation Data " , "SUCCESS");
                } else {
                    Log.i("Reservation Data " , "FAILED");
                }
            } else {
                Log.i("Server Connection", "ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayConfirmAlert()  {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
        builder.setTitle("예약 하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertReservationData(reservationDTO);
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    private void displayTableSelectionDialog() {
        createTableOption(reservationDTO.getRTime());
        tableGrid = tableBinding.tableGrid;
        if (tableGrid.getParent() != null) {
            ((ViewGroup)tableGrid.getParent()).removeView(tableGrid);
        }

        final List<Integer> selectedTables = new ArrayList<>();
        tableGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TableListItemView itemView= (TableListItemView) tableItemAdapter.getView(position, null, null);
                if (itemView.getIsSelectable()) {
                    selectedTables.add(position + 1);
                    itemView.setIsSelectable(false);
                    itemView.setTableDisable();
                } else {
                    itemView.setIsSelectable(true);
                    itemView.setTableEnable();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
        builder.setTitle("예약하고자 하는 테이블을 선택하세요");
        builder.setView(tableGrid);
        builder.setCancelable(false);
        builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tables = "";
                String tableString = "";
                for (int i = 0; i < selectedTables.size(); i++) {
                    tables = tables + selectedTables.get(i) + " ";
                    tableString = tableString + "테이블 " + selectedTables.get(i) + " ";
                    disableTableOption(reservationDTO.getRTime(), selectedTables.get(i));
                }
                reservationDTO.setRTable(reservationDTO.getRNum());
                sequenceStep4.setTitle(tableString);
                sequenceStep3.setActive(false);
                sequenceStep4.setActive(true);
                buttonPickMenu.setEnabled(true);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

}
