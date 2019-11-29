package uwi.comp6901.klbakery.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import uwi.comp6901.klbakery.db.converter.DateConverter;
import uwi.comp6901.klbakery.db.dao.DeliveryRunDao;
import uwi.comp6901.klbakery.db.dao.DeliveryRunInvoiceDao;
import uwi.comp6901.klbakery.db.dao.InvoiceDao;
import uwi.comp6901.klbakery.db.dao.InvoiceDetailDao;
import uwi.comp6901.klbakery.db.dao.OrderDao;
import uwi.comp6901.klbakery.db.dao.OrderDetailDao;
import uwi.comp6901.klbakery.db.dao.ProductDao;
import uwi.comp6901.klbakery.db.dao.RouteDao;
import uwi.comp6901.klbakery.db.dao.UserDao;
import uwi.comp6901.klbakery.db.dao.UserRouteDao;
import uwi.comp6901.klbakery.db.entity.DeliveryRun;
import uwi.comp6901.klbakery.db.entity.DeliveryRunInvoice;
import uwi.comp6901.klbakery.db.entity.Invoice;
import uwi.comp6901.klbakery.db.entity.InvoiceDetail;
import uwi.comp6901.klbakery.db.entity.Order;
import uwi.comp6901.klbakery.db.entity.OrderDetail;
import uwi.comp6901.klbakery.db.entity.Product;
import uwi.comp6901.klbakery.db.entity.Route;
import uwi.comp6901.klbakery.db.entity.User;
import uwi.comp6901.klbakery.db.entity.UserRoute;

import static android.content.ContentValues.TAG;

@Database(entities = {User.class, Route.class, UserRoute.class, Product.class, Order.class, OrderDetail.class,
        Invoice.class, InvoiceDetail.class, DeliveryRun.class, DeliveryRunInvoice.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class BakeryDatabase extends RoomDatabase {

    private static BakeryDatabase instance;


    public abstract UserDao userDao();

    public abstract RouteDao routeDao();

    public abstract UserRouteDao userRouteDao();

    public abstract ProductDao productDao();

    public abstract OrderDao orderDao();

    public abstract OrderDetailDao orderDetailDao();

    public abstract InvoiceDao invoiceDao();

    public abstract InvoiceDetailDao invoiceDetailDao();

    public abstract DeliveryRunDao deliveryRunDao();

    public abstract DeliveryRunInvoiceDao deliveryRunInvoiceDao();

    public static synchronized BakeryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BakeryDatabase.class, "kl_bakery_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //insert defaults rows
            new PopulateDbAsyncTask(instance).execute();
        }

        /*@Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateProductAsyncTask(instance).execute();

        }*/
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;
        private ProductDao productDao;

        private PopulateDbAsyncTask(BakeryDatabase db) {
            userDao = db.userDao();
            productDao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            //insert some users
            userDao.insert(new User("Customer","kumar","kumar@gmail.com","password","Cumuto"));
            userDao.insert(new User("Driver","kimberly","kimberly@gmail.com","password","St. Augustine"));
            userDao.insert(new User("Store Rep","jayron","jayron@gmail.com","password","San Fernando"));

            //insert some products
            productDao.insert(new Product("Hops", 10.00, 250));
            productDao.insert(new Product("Slice Bread", 12.00, 250));
            productDao.insert(new Product("Drops", 3.00, 400));
            productDao.insert(new Product("Currents Roll", 5.00, 200));
            productDao.insert(new Product("Slice Cake", 8.00, 80));
            return null;
        }
    }
}
