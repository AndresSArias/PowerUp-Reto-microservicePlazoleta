package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotAllowedForCreationException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

@RequiredArgsConstructor
@Transactional
public class UserMysqlAdapter implements IUserPersistencePort {


    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    private final IUserEntityMapper userEntityMapper;




    @Override
    public void saveUserOwner(User user) {

        if (!user.getRole().getId().equals(OWNER_ROLE_ID))
        {
            throw new RoleNotAllowedForCreationException();
        }
        if (userRepository.findByNumberDocument(user.getNumberDocument()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        roleRepository.findById(user.getRole().getId()).orElseThrow(RoleNotFoundException::new);

        userRepository.save(userEntityMapper.toEntity(user));
    }

    private boolean validateFormatDate(User user) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


        try {
            Date date = sdf.parse(user.getDateBirth()+"");
            return  true;
        } catch (ParseException e) {
            return false;
        }

    }

}
