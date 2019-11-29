package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.ProductDao;
import uwi.comp6901.klbakery.db.entity.Product;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> loadAllData;

    public ProductRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        productDao = db.productDao();
    }

    public void insert(Product product){
        new InsertProductAsyncTask(productDao).execute(product);
    }

    public void update(Product product){
        new UpdateProductAsyncTask(productDao).execute(product);
    }

    public void delete(Product product){
        new DeleteProductAsyncTask(productDao).execute(product);
    }

    public LiveData<Product> loadProduct(int id){
        return productDao.loadProduct(id);
    }

    public LiveData<List<Product>> searchAllProducts(String query){
        return productDao.searchAllProducts(query);
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private InsertProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }

     private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao productDao;

        private UpdateProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products){
            productDao.update(products[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao productDao;

        private DeleteProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products){
            productDao.delete(products[0]);
            return null;
        }
    }


}
