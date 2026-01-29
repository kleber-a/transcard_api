package com.kleber.transcard.repository.projection;

public interface CardWithUserProjection {
    Long getId();
    Long getCardNumber();
    String getCardName();
    Boolean getStatus();
    String getTypeCard();

    Long getUserId();
    String getUserName();
    String getUserEmail();

}
