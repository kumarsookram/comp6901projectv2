package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.OrderDao;
import uwi.comp6901.klbakery.db.entity.Order;

public class OrderRepository {
    private OrderDao orderDao;

    public OrderRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        orderDao = db.orderDao();
    }

    public void insert(Order order){
        new InsertOrderAsyncTask(orderDao).execute(order);
    }

    public void update(Order order){
        new UpdateOrderAsyncTask(orderDao).execute(order);
    }

    public void delete(Order order){
        new DeleteOrderAsyncTask(orderDao).execute(order);
    }

    public LiveData<List<Order>> customerOrders(int customerId){
        return orderDao.customerOrders(customerId);
    }

    public LiveData<Order> loadOrder(int orderId){
        return orderDao.loadOrder(orderId);
    }

    private static class InsertOrderAsyncTask extends AsyncTask<Order, Void, Void>{

        private OrderDao orderDao;

        private InsertOrderAsyncTask(OrderDao orderDao){
            this.orderDao  = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insert(orders[0]);
            return null;
        }

    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Order, Void, Void>{

        private OrderDao orderDao;

        private UpdateOrderAsyncTask(OrderDao orderDao){
            this.orderDao  = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.update(orders[0]);
            return null;
        }

    }

    private static class DeleteOrderAsyncTask extends AsyncTask<Order, Void, Void>{

        private OrderDao orderDao;

        private DeleteOrderAsyncTask(OrderDao orderDao){
            this.orderDao  = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.delete(orders[0]);
            return null;
        }

    }

}
