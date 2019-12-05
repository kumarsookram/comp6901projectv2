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

import java.util.Date;

import uwi.comp6901.klbakery.db.converter.DateConverter;
import uwi.comp6901.klbakery.db.dao.DeliveryRunDao;
import uwi.comp6901.klbakery.db.dao.DeliveryRunInvoiceDao;
import uwi.comp6901.klbakery.db.dao.InvoiceDao;
import uwi.comp6901.klbakery.db.dao.InvoiceDetailDao;
import uwi.comp6901.klbakery.db.dao.JoinDriverDeliveryRouteDao;
import uwi.comp6901.klbakery.db.dao.JoinInvoiceOrderDao;
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
import uwi.comp6901.klbakery.db.entity.JoinInvoiceOrder;
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

    public abstract JoinDriverDeliveryRouteDao joinDriverDeliveryRouteDao();

    public abstract JoinInvoiceOrderDao joinInvoiceOrderDao();

    public static synchronized BakeryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BakeryDatabase.class, "kl_bakery_databaseTest2")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
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
        private UserRouteDao userRouteDao;
        private RouteDao routeDao;
        private OrderDao orderDao;
        private OrderDetailDao orderDetailDao;
        private InvoiceDao invoiceDao;
        private InvoiceDetailDao invoiceDetailDao;
        private DeliveryRunDao deliveryRunDao;
        private DeliveryRunInvoiceDao deliveryRunInvoiceDao;
        //private JoinDriverDeliveryRouteDao joinDriverDeliveryRouteDao;

        private PopulateDbAsyncTask(BakeryDatabase db) {
            userDao = db.userDao();
            productDao = db.productDao();
            userRouteDao = db.userRouteDao();
            routeDao = db.routeDao();
            orderDao = db.orderDao();
            orderDetailDao = db.orderDetailDao();
            invoiceDao = db.invoiceDao();
            invoiceDetailDao = db.invoiceDetailDao();
            deliveryRunDao = db.deliveryRunDao();
            deliveryRunInvoiceDao = db.deliveryRunInvoiceDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            //insert some users
            userDao.insert(new User("Store Rep","kumar","kumar@gmail.com","password","Cumuto"));
            userDao.insert(new User("Driver","kimberly","kimberly@gmail.com","password","St. Augustine"));
            userDao.insert(new User("Customer","jayron","jayron@gmail.com","password","San Fernando"));
            userDao.insert(new User("Customer","donald","donald@gmail.com","password","Mt Hope"));
            userDao.insert(new User("Customer","donna","donna@gmail.com","password","Arima"));
            userDao.insert(new User("Customer","dianne","dianne@gmail.com","password","Santa Cruz"));
            userDao.insert(new User("Customer","john","john@gmail.com","password","San Fernando"));


            //insert some products
            productDao.insert(new Product("Hops", 10.00, 250));
            productDao.insert(new Product("Slice Bread", 12.00, 250));
            productDao.insert(new Product("Drops", 3.00, 400));
            productDao.insert(new Product("Currents Roll", 5.00, 200));
            productDao.insert(new Product("Slice Cake", 8.00, 80));

            //insert some routes
            routeDao.insert(new Route("East", "East Region"));
            routeDao.insert(new Route("West", "West Region"));
            routeDao.insert(new Route("South", "South Region"));

            //insert user routes
            userRouteDao.insert(new UserRoute(3,1,new Date()));
            userRouteDao.insert(new UserRoute(4,2,new Date()));
            userRouteDao.insert(new UserRoute(5,3,new Date()));
            userRouteDao.insert(new UserRoute(6,1,new Date()));
            userRouteDao.insert(new UserRoute(7,2,new Date()));

            //insert some orders
            orderDao.insert(new Order(3, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(4, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(5, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(6, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(7, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(3, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(4, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(5, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(6, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(7, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(3, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(4, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(5, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(6, new Date(),new Date(), "Pending" ));
            orderDao.insert(new Order(7, new Date(),new Date(), "Pending" ));

            //insert some order details
            orderDetailDao.insert(new OrderDetail(1,1,4));
            orderDetailDao.insert(new OrderDetail(1,2,2));
            orderDetailDao.insert(new OrderDetail(1,4,1));
            orderDetailDao.insert(new OrderDetail(2,2,1));
            orderDetailDao.insert(new OrderDetail(2,3,2));
            orderDetailDao.insert(new OrderDetail(2,4,4));
            orderDetailDao.insert(new OrderDetail(3,1,4));
            orderDetailDao.insert(new OrderDetail(3,3,5));
            orderDetailDao.insert(new OrderDetail(4,5,2));
            orderDetailDao.insert(new OrderDetail(4,4,2));
            orderDetailDao.insert(new OrderDetail(4,2,3));
            orderDetailDao.insert(new OrderDetail(5,1,4));
            orderDetailDao.insert(new OrderDetail(5,2,4));
            orderDetailDao.insert(new OrderDetail(5,3,4));
            orderDetailDao.insert(new OrderDetail(6,1,4));
            orderDetailDao.insert(new OrderDetail(6,3,4));
            orderDetailDao.insert(new OrderDetail(7,1,4));
            orderDetailDao.insert(new OrderDetail(7,2,4));
            orderDetailDao.insert(new OrderDetail(8,1,4));
            orderDetailDao.insert(new OrderDetail(8,3,4));
            orderDetailDao.insert(new OrderDetail(9,1,4));
            orderDetailDao.insert(new OrderDetail(9,5,4));
            orderDetailDao.insert(new OrderDetail(10,1,4));
            orderDetailDao.insert(new OrderDetail(10,2,4));
            orderDetailDao.insert(new OrderDetail(11,3,4));
            orderDetailDao.insert(new OrderDetail(12,1,4));
            orderDetailDao.insert(new OrderDetail(12,3,4));
            orderDetailDao.insert(new OrderDetail(12,2,4));
            orderDetailDao.insert(new OrderDetail(12,4,4));
            orderDetailDao.insert(new OrderDetail(13,1,4));
            orderDetailDao.insert(new OrderDetail(13,2,4));
            orderDetailDao.insert(new OrderDetail(13,3,4));
            orderDetailDao.insert(new OrderDetail(13,4,4));
            orderDetailDao.insert(new OrderDetail(13,5,4));
            orderDetailDao.insert(new OrderDetail(14,1,4));
            orderDetailDao.insert(new OrderDetail(14,2,4));
            orderDetailDao.insert(new OrderDetail(14,3,4));
            orderDetailDao.insert(new OrderDetail(14,4,4));
            orderDetailDao.insert(new OrderDetail(14,5,4));
            orderDetailDao.insert(new OrderDetail(15,1,4));
            orderDetailDao.insert(new OrderDetail(15,2,4));
            orderDetailDao.insert(new OrderDetail(15,3,4));
            orderDetailDao.insert(new OrderDetail(15,5,4));

            //insert some invoices
            invoiceDao.insert(new Invoice(1,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(2,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(3,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(4,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(5,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(6,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(7,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(8,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(9,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(10,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(11,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(12,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(13,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(14,new Date(), new Date(), "Pending", ""));
            invoiceDao.insert(new Invoice(15,new Date(), new Date(), "Pending", ""));

            //insert some invoice details
            //Todo, a query can update the data
            invoiceDetailDao.insert(new InvoiceDetail(1,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(1,"Slice Bread",2,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(1,"Currents Roll",4,10.0));
            //2
            invoiceDetailDao.insert(new InvoiceDetail(2,"Slice Bread",2,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(2,"Drops",2,3.0));
            invoiceDetailDao.insert(new InvoiceDetail(2,"Currents Roll",4,150));

            //3
            invoiceDetailDao.insert(new InvoiceDetail(3,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(3,"Drops",5,3.0));

            //4
            invoiceDetailDao.insert(new InvoiceDetail(4,"Slice Cake",2,8.0));
            invoiceDetailDao.insert(new InvoiceDetail(4,"Currents Roll",2,5.0));
            invoiceDetailDao.insert(new InvoiceDetail(4,"Slice Bread",3,12.0));

            //5
            invoiceDetailDao.insert(new InvoiceDetail(5,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(5,"Slice Bread",4,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(5,"Drops",4,3.0));

            //6
            invoiceDetailDao.insert(new InvoiceDetail(6,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(6,"Drops",4,3.0));

            //7
            invoiceDetailDao.insert(new InvoiceDetail(7,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(7,"Slice Bread",4,12.0));

            //8
            invoiceDetailDao.insert(new InvoiceDetail(8,"Slice Cake",4,8.0));
            invoiceDetailDao.insert(new InvoiceDetail(8,"Drops",4,3.0));

            //9
            invoiceDetailDao.insert(new InvoiceDetail(9,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(9,"Slice cake",4,8.0));

            //10
            invoiceDetailDao.insert(new InvoiceDetail(10,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(10,"Slice Bread",4,12.0));


            //11
            invoiceDetailDao.insert(new InvoiceDetail(11,"Drops",4,3.0));

            //12
            invoiceDetailDao.insert(new InvoiceDetail(12,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(12,"Drops",4,3.0));
            invoiceDetailDao.insert(new InvoiceDetail(12,"Slice Bread",4,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(12,"Currents Roll",4,5.0));


            //13
            invoiceDetailDao.insert(new InvoiceDetail(13,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(13,"Slice Bread",4,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(13,"Drops",4,3.0));
            invoiceDetailDao.insert(new InvoiceDetail(13,"Currents Roll",4,5.0));
            invoiceDetailDao.insert(new InvoiceDetail(13,"Slice cake",4,8.0));

            //14

            invoiceDetailDao.insert(new InvoiceDetail(14,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(14,"Slice Bread",4,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(14,"Drops",4,3.0));
            invoiceDetailDao.insert(new InvoiceDetail(14,"Currents Roll",4,5.0));

            //15
            invoiceDetailDao.insert(new InvoiceDetail(15,"Hops",4,10.0));
            invoiceDetailDao.insert(new InvoiceDetail(15,"Slice Bread",4,12.0));
            invoiceDetailDao.insert(new InvoiceDetail(15,"Drops",4,3.0));
            invoiceDetailDao.insert(new InvoiceDetail(15,"Slice cake",4,8.0));





            //insert some Delivery run
            deliveryRunDao.insert(new DeliveryRun(2, 1,new Date(), "Pending"));
            deliveryRunDao.insert(new DeliveryRun(2, 2,new Date(), "Pending"));
            deliveryRunDao.insert(new DeliveryRun(2, 3,new Date(), "Pending"));

            //insert some Delivery run invoices

            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,1,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,2,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,3,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,4,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,5,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,6,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(1,7,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(2,8,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(2,9,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(2,10,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(2,11,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(2,12,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(3,13,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(3,14,new Date()));
            deliveryRunInvoiceDao.insert(new DeliveryRunInvoice(3,15,new Date()));

            return null;
        }
    }
}
