package uwi.comp6901.klbakery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.User;
import uwi.comp6901.klbakery.repository.UserRepository;


public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void insert(User user){
        userRepository.insert(user);
    }

    public void update(User user){
        userRepository.update(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }


    public LiveData<User>getUser(int id){
        return userRepository.getUser(id);
    }

    public LiveData<User> getUser(String email){
        return userRepository.getUser(email);
    }

    public LiveData<User>validate(String email, String password){
        return userRepository.validate(email, password);
    }

    public LiveData<List<User>> getAllUsers(){ return userRepository.getAllUser();}
}
