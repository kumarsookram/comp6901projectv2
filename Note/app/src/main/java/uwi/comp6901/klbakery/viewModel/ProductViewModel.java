package uwi.comp6901.klbakery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.Product;
import uwi.comp6901.klbakery.repository.ProductRepository;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;
    //private LiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application);
        //this.allProducts = productRepository.searchAllProducts("*");
    }

    public void insert(Product product){
        productRepository.insert(product);
    }

    public void update(Product product){
        productRepository.update(product);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

    public LiveData<Product> loadProduct(int productId){
        return productRepository.loadProduct(productId);
    }

    public LiveData<List<Product>> searchAllProducts(String query){
        return productRepository.searchAllProducts(query);
    }

}
