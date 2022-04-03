package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

import javax.servlet.http.Part;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Participant findByLogin(String login) {
		for (Participant participant : getAll()) {
			if (participant.getLogin().equals(login)) {
				return participant;
			}
		}
		return null;
	}

	public void addParticipant(Participant participant) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(participant);
		session.getTransaction().commit();
	}

	public void delete(Participant participant) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(participant);
		session.getTransaction().commit();
	}

	public void updateParticipant(Participant participant) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(participant);
		session.getTransaction().commit();
	}
}
