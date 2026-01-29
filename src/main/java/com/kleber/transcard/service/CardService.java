package com.kleber.transcard.service;

import com.kleber.transcard.dto.response.CardDTO;
import com.kleber.transcard.dto.request.CreateCardDTO;
import com.kleber.transcard.entity.Card;
import com.kleber.transcard.entity.User;
import com.kleber.transcard.exceptions.cards.CardAlreadyExistsException;
import com.kleber.transcard.exceptions.cards.CardNotFoundException;
import com.kleber.transcard.exceptions.common.UserNotFoundException;
import com.kleber.transcard.mapper.CardMapper;
import com.kleber.transcard.repository.CardRepository;
import com.kleber.transcard.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, UserRepository userRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.cardMapper = cardMapper;
    }

    public Page<CardDTO> getCards(String name, String typeCard, Boolean status, Pageable pageable) {
        return cardRepository.findByFilters(name, typeCard, status, pageable)
                .map(cardMapper::toDto);
    }

    public CardDTO createCardForUser(Long targetUserId, CreateCardDTO dto) {

        if (cardRepository.existsByCardNumber(dto.cardNumber())) {
            throw new CardAlreadyExistsException();
        }

        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(UserNotFoundException::new);


        Card card = cardMapper.toEntity(dto);
        card.setStatus(true);
        card.setUser(targetUser);

        return cardMapper.toDTO(cardRepository.save(card));
    }


    public CardDTO toggleStatus(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(CardNotFoundException::new);

        card.setStatus(!card.getStatus());
        return cardMapper.toDTO(cardRepository.save(card));
    }

    @Transactional
    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(CardNotFoundException::new);

        cardRepository.delete(card);
    }



}
