package uwi.comp6901.klbakery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public boolean validate(User user, String password){
        if (user.getUser_password().equals(password)) {
            return true;
        }else {
            return false;
        }
    }
}
