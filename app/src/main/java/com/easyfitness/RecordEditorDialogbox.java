package com.easyfitness;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easyfitness.DAO.record.DAORecord;
import com.easyfitness.DAO.record.Record;
import com.easyfitness.enums.DistanceUnit;
import com.easyfitness.enums.ProgramRecordStatus;
import com.easyfitness.enums.RecordType;
import com.easyfitness.enums.WeightUnit;
import com.easyfitness.utils.UnitConverter;
import com.easyfitness.views.WorkoutValuesInputView;

public class RecordEditorDialogbox extends Dialog implements View.OnClickListener {

    private final boolean mShowRestTime;
    private final Activity mActivity;
    private final Record mRecord;
    public Dialog d;
    private WorkoutValuesInputView mWorkoutValuesInput;
    private boolean mCancelled = false;

    public RecordEditorDialogbox(Activity a, Record record) {
        super(a);
        this.mActivity = a;
        mRecord = record;
        mShowRestTime = false;
    }

    public RecordEditorDialogbox(Activity a, Record record, boolean showRestTime) {
        super(a);
        this.mActivity = a;
        mRecord = record;
        mShowRestTime = showRestTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_record_editor);
        this.setCanceledOnTouchOutside(false);

        Button updateButton = findViewById(R.id.btn_update);
        Button failedButton = findViewById(R.id.btn_failed);
        Button cancelButton = findViewById(R.id.btn_cancel);
        mWorkoutValuesInput = findViewById(R.id.EditorWorkoutValuesInput);

        mWorkoutValuesInput.setRecord(mRecord);
        mWorkoutValuesInput.setShowRestTime(mShowRestTime);

        if (mRecord.getRecordType() == RecordType.PROGRAM_RECORD_TYPE) {
            updateButton.setText(getContext().getString(R.string.success));
            failedButton.setVisibility(View.VISIBLE);
            failedButton.setText(getContext().getString(R.string.fail));
        } else {
            updateButton.setText(getContext().getString(R.string.update));
            failedButton.setVisibility(View.GONE);
        }

        updateButton.setOnClickListener(this);
        failedButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cancel) {
            mCancelled = true;
            cancel();
        } else if (v.getId() == R.id.btn_update || v.getId() == R.id.btn_failed ) {
            // update record
            DAORecord daoRecord = new DAORecord(mActivity.getBaseContext());
            switch (mRecord.getExerciseType()) {
                case CARDIO:
                    float distance = mWorkoutValuesInput.getDistanceValue();
                    if (mWorkoutValuesInput.getDistanceUnit() == DistanceUnit.MILES) {
                        distance = UnitConverter.MilesToKm(distance); // Always convert to KG
                    }
                    mRecord.setDuration(mWorkoutValuesInput.getDurationValue());
                    mRecord.setDistance(distance);
                    mRecord.setDistanceUnit(mWorkoutValuesInput.getDistanceUnit());
                    break;
                case ISOMETRIC:
                    float tmpPoids = mWorkoutValuesInput.getWeightValue();
                    tmpPoids = UnitConverter.weightConverter(tmpPoids, mWorkoutValuesInput.getWeightUnit(), WeightUnit.KG); // Always convert to KG

                    mRecord.setSets(mWorkoutValuesInput.getSets());
                    mRecord.setSeconds(mWorkoutValuesInput.getSeconds());
                    mRecord.setWeight(tmpPoids);
                    mRecord.setWeightUnit(mWorkoutValuesInput.getWeightUnit());
                    break;
                case STRENGTH:
                    float tmpWeight = mWorkoutValuesInput.getWeightValue();
                    tmpPoids = UnitConverter.weightConverter(tmpWeight, mWorkoutValuesInput.getWeightUnit(), WeightUnit.KG); // Always convert to KG
                    mRecord.setSets(mWorkoutValuesInput.getSets());
                    mRecord.setReps(mWorkoutValuesInput.getReps());
                    mRecord.setWeight(tmpPoids);
                    mRecord.setWeightUnit(mWorkoutValuesInput.getWeightUnit());
                    break;
            }
            if (mShowRestTime) {
                if (mWorkoutValuesInput.isRestTimeActivated()) {
                    mRecord.setRestTime(mWorkoutValuesInput.getRestTime());
                } else {
                    mRecord.setRestTime(0);
                }
            }
            if(v.getId() == R.id.btn_update) {
                mRecord.setProgramRecordStatus(ProgramRecordStatus.SUCCESS);
            } else if (v.getId() == R.id.btn_failed) {
                mRecord.setProgramRecordStatus(ProgramRecordStatus.FAILED);
            }

            daoRecord.updateRecord(mRecord);
            mCancelled = false;
            dismiss();
        }
    }

    public boolean isCancelled() {
        return mCancelled;
    }

}
