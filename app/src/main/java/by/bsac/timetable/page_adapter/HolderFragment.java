package by.bsac.timetable.page_adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import by.bsac.timetable.R;
import by.bsac.timetable.resourc.records.Record;

import java.util.ArrayList;


public class HolderFragment extends Fragment {

    private ArrayList<Record> list;

    public HolderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      final   View mMiew = getLayoutInflater().inflate(R.layout.content_workspace,null);

        FrameLayout frameLayout= mMiew.findViewById(R.id.fragment_container);

        LinearLayout lin = new LinearLayout(getActivity());
        LinearLayout.LayoutParams paramsBody = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lin.setLayoutParams(paramsBody);
        lin.setOrientation(LinearLayout.VERTICAL);

//        ViewGroup.LayoutParams paramsWrap = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams paramsMath = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams paramsBody = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        paramsBody.setMargins(0, 15, 0, 0);
//
//        lin.setLayoutParams(paramsBody);
//        lin.setOrientation(LinearLayout.VERTICAL);
//
//        if (this.list != null) {
//            for (int i = 0; i < list.size(); i++) {
//                Record record = list.get(i);
//
//
//                LinearLayout linearBody = new LinearLayout(getActivity());
//
//
//                linearBody.setLayoutParams(paramsBody);
//                linearBody.setOrientation(LinearLayout.VERTICAL);
//                linearBody.setBackgroundColor(Color.WHITE);
//                linearBody.setBackgroundResource(R.drawable.cornerlistview);
//
//
//                LinearLayout linerUp = new LinearLayout(getActivity());
//                linerUp.setOrientation(LinearLayout.HORIZONTAL);
//                linerUp.setLayoutParams(paramsMath);
//
//
//                TextView txtTimeBegin = new TextView(getActivity());
//                txtTimeBegin.setText(getTimeBegin(record.getOrdinal_number()));
//                txtTimeBegin.setLayoutParams(paramsWrap);
//                linerUp.addView(txtTimeBegin);
//
//
//                TextView txtLect = new TextView(getActivity());
//                txtLect.setText(String.valueOf(record.getNumder() + " ауд. Корпус: " + record.getBuilding()));
//                txtLect.setLayoutParams(paramsMath);
//                txtLect.setGravity(RIGHT);
//
//                linerUp.addView(txtLect);
//
//                LinearLayout linLayout = new LinearLayout(getActivity());
//                linLayout.setOrientation(LinearLayout.VERTICAL);
//                linLayout.setLayoutParams(paramsMath);
//
//
//                TextView txtType = new TextView(getActivity());
//                txtType.setText(record.getType());
//                txtType.setBackgroundColor(Color.BLUE);
//                txtType.setTextColor(Color.WHITE);
//                txtType.setLayoutParams(paramsWrap);
//                linLayout.addView(txtType);
//
//                TextView name_sabgekt = new TextView(getActivity());
//                name_sabgekt.setText(record.getSubject());
//                name_sabgekt.setLayoutParams(paramsWrap);
//                name_sabgekt.setTextSize(20);
//                linLayout.addView(name_sabgekt);
//
//                TextView txtLecturer = new TextView(getActivity());
//                txtLecturer.setText(record.getLecturer());
//                txtLecturer.setLayoutParams(paramsWrap);
//                linLayout.addView(txtLecturer);
//
//                TextView txtSabgektFor = new TextView(getActivity());
//                txtSabgektFor.setText(record.getSubjectFor());
//                txtSabgektFor.setLayoutParams(paramsWrap);
//                linLayout.addView(txtSabgektFor);
//
//                linearBody.addView(linerUp);
//                linearBody.addView(linLayout);
//
//                lin.addView(linearBody);
//
//            }
//        } else {
//            TextView norecord = new TextView(getActivity());
//            norecord.setText("Занятий нет!");
//            norecord.setLayoutParams(paramsMath);
//            norecord.setGravity(CENTER);
//            lin.addView(norecord);
//
//        }


        if(list!=null) {
            for (Record item:list) {
                View view = getLayoutInflater().inflate(R.layout.test,null);
                lin.addView(writeViewFromRecord(item,view));
            }
        }else {
//             TODO
//             если занятий нет
        }

        //добавить запись в ViewGroup
        frameLayout.addView(lin);

        return mMiew;
    }

    public void setList(ArrayList<Record> list) {
        this.list = list;
    }

    private String getTimeBegin(int less) {
        String time = "";
        Resources resources = getActivity().getResources();
        switch (less) {
            case 1: {
                time = resources.getString(R.string.less1);
                break;
            }
            case 2: {
                time = resources.getString(R.string.less2);
                break;
            }
            case 3: {
                time = resources.getString(R.string.less3);
                break;
            }
            case 4: {
                time = resources.getString(R.string.less4);
                break;
            }
            case 5: {
                time = resources.getString(R.string.less5);
                break;
            }
            case 6: {
                time = resources.getString(R.string.less6);
                break;
            }
            default:
                time = String.valueOf(less) + "ошибка";

        }

        return time;
    }


    private View writeViewFromRecord(Record record, View view){

        TextView begin = view.findViewById(R.id.begin);
        begin.setText(getTimeBegin(record.getOrdinal_number()));
        TextView room=view.findViewById(R.id.room);
        room.setText((record.getNumder()+" ауд. "+record.getBuilding()+" корп."));
        TextView type =view.findViewById(R.id.type);
        setTextViewColorByType(record.getType(),type);
        type.setText(record.getType());
        TextView lesson= view.findViewById(R.id.lesson);
        lesson.setText(record.getSubject());
        TextView subg_for=view.findViewById(R.id.subg_for);
        subg_for.setText(record.getSubjectFor());
        TextView teacher =view.findViewById(R.id.teacher);
        teacher.setText(record.getLecturer());

        return view;
    }


    private void setTextViewColorByType(String type, TextView view){
        switch (type){
            case  "Лекция": view.setBackgroundResource(R.color.subj_type_lect);
            break;
            case "Лабораторная работа":view.setBackgroundResource(R.color.subj_type_pract);
            break;
            case "Практическая работа":view.setBackgroundResource(R.color.subj_type_lab);
            break;
            default: view.setBackgroundResource(R.color.subj_type_def);
            view.setTextColor(Color.BLACK);
            break;
        }



    }

}
