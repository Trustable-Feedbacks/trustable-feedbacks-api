package org.app1.trustablefeedback.DTO.Feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponseDTO<T> {
    private List<T> returnList;

    public void addItem(T item){
        returnList.add(item);
    }
}
