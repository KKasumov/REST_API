package com.kasumov.rest_api.repository.impl;

import com.kasumov.rest_api.repository.EventRepository;
import com.kasumov.rest_api.model.Event;
import com.kasumov.rest_api.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    private Transaction transaction;

    @Override
    public Event getById(Integer id) {
        Event event = new Event();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            event = session.get(Event.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> eventList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Event> query = session.createQuery("SELECT e FROM Event e", Event.class);
            eventList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    @Override
    public Event create(Event event) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(event);
            String hql = "Update Event "
                    + "SET user.id = :userId "
                    + ", file.id  = :fileId "
                    + " where id = :eventId";
            session.createQuery(hql)
                    .setParameter("eventId", event.getId())
                    .setParameter("userId", event.getUser().getId())
                    .setParameter("fileId", event.getFile().getId())
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Event WHERE id = :param")
                    .setParameter("param", id)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
