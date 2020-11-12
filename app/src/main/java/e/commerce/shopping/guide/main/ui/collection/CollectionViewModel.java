package e.commerce.shopping.guide.main.ui.collection;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CollectionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CollectionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is collection fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}