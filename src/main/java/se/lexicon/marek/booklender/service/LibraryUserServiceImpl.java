package se.lexicon.marek.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marek.booklender.Entity.LibraryUser;
import se.lexicon.marek.booklender.Repository.LibraryUserRepository;
import se.lexicon.marek.booklender.dto.LibraryUserDto;

import java.util.List;
import java.util.Optional;

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

    @Override
    public LibraryUserDto findByEmail(String email) {
        if (email == null)  throw new IllegalArgumentException("");
        LibraryUser libraryUser = libraryUserRepository.findByEmail(email);
        LibraryUserDto dto = modelMapper.map(libraryUser, LibraryUserDto.class);
        return dto;
        }


    @Override
    public List<LibraryUserDto> findAll() {
        return null;
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
        return false;
    }
}
