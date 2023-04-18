package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.Affairs;

import java.util.List;

public interface GetListViewAffairs {
    ListView<Affairs> getListViewMail(List<Affairs> affairsList);
}
