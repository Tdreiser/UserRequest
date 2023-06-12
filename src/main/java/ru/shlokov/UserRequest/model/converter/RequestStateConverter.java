package ru.shlokov.UserRequest.model.converter;

import javax.persistence.AttributeConverter;
import ru.shlokov.UserRequest.model.enums.RequestState;



/**
 * @author Shlokov Andrey
 */
public class RequestStateConverter implements AttributeConverter<RequestState, String> {
    @Override
    public String convertToDatabaseColumn(RequestState attribute) {
        if (attribute == null)
            return null;
        switch (attribute) {
            case draft:
                return "draft";
            case decided:
                return "decided";
            case accepted:
                return "accepted";
            case sent:
                return "sent";
            default:
                throw new IllegalArgumentException(attribute + " not supported");
        }
    }

    @Override
    public RequestState convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        switch (dbData) {
            case "draft":
                return RequestState.draft;
            case "decided":
                return RequestState.decided;
            case "accepted":
                return RequestState.accepted;
            case "sent":
                return RequestState.sent;
            default:
                throw new IllegalArgumentException(dbData + " not supported");
        }
    }
}
