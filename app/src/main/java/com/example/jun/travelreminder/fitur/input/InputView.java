package com.example.jun.travelreminder.fitur.input;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.base.BaseView;

public interface InputView extends BaseView {
    void showData(item item_list);

    void showDate(DateUsers dateUsers);
}
