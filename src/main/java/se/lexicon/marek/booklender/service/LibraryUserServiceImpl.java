package se.lexicon.marek.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marek.booklender.Entity.LibraryUser;
import se.lexicon.marek.booklender.Repository.LibraryUserRepository;
import se.lexicon.marek.booklender.dto.LibraryUserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {
    LibraryUserRepository libraryUserRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LibraryUserDto findById(int userId) {
        if (userId == 0) throw new IllegalArgumentException("");
        Optional<LibraryUser> optional = libraryUserRepository.findById(userId);
        if (optional.isPresent()) {
            LibraryUserDto dto = modelMapper.map(optional.get(), LibraryUserDto.class);
            return dto;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    //todo:--------------------
    @Override
    public LibraryUserDto findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email should not be null");
        Optional<LibraryUser> optional = libraryUserRepository.findByEmail(email);
        if (optional.isPresent()) {
            LibraryUserDto dto = modelMapper.map(optional.get(), LibraryUserDto.class);
            return dto;
        } else {
            return null;
        }

    }


    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(libraryUserList::add);
        List<LibraryUserDto> libraryUserDtos = libraryUserList.stream().map(libraryUser -> modelMapper.map(libraryUser, LibraryUserDto.class)).collect(Collectors.toList());
        return libraryUserDtos;
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        if (libraryUserDto == null) throw new IllegalArgumentException("");
        if (libraryUserDto.getUserId() != 0) throw new IllegalArgumentException("");
        LibraryUser libraryUserEntity = modelMapper.map(libraryUserDto, LibraryUser.class);
        LibraryUser savedEntity = libraryUserRepository.save(libraryUserEntity);
        LibraryUserDto converted = modelMapper.map(savedEntity, LibraryUserDto.class);
        return converted;
    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) {
        if (libraryUserDto == null) throw new IllegalArgumentException("");
        if (libraryUserDto.getUserId() == 0) throw new IllegalArgumentException("");
        Optional<LibraryUser> libraryUser = libraryUserRepository.findById(libraryUserDto.getUserId());
        if (libraryUser.isPresent()) {
            return modelMapper.map(libraryUserRepository.save(modelMapper
                    .map(libraryUserDto, LibraryUser.class)), LibraryUserDto.class);
        } else {
            throw new IllegalArgumentException("");
        }

    }

    @Override
    public boolean delete(int userId) {
        if (userId == 0) throw new IllegalArgumentException("");
        Optional<LibraryUser> optional = libraryUserRepository.findById(userId);
        if (optional.isPresent()) {
            libraryUserRepository.deleteById(userId);
            return true;
        }

        return false;
    }
}
