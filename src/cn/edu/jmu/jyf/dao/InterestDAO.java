package cn.edu.jmu.jyf.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.jmu.jyf.bean.Interest;
import cn.edu.jmu.jyf.bean.InterestId;

/**
 * A data access object (DAO) providing persistence and search support for
 * Interest entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.jmu.jyf.bean.Interest
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class InterestDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InterestDAO.class);
	// property constants
	public static final String WEIGHT = "weight";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Interest transientInstance) {
		log.debug("saving Interest instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Interest persistentInstance) {
		log.debug("deleting Interest instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Interest findById(cn.edu.jmu.jyf.bean.InterestId id) {
		log.debug("getting Interest instance with id: " + id);
		try {
			Interest instance = (Interest) getCurrentSession().get(
					"cn.edu.jmu.jyf.bean.Interest", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Interest> findByExample(Interest instance) {
		log.debug("finding Interest instance by example");
		try {
			List<Interest> results = (List<Interest>) getCurrentSession()
					.createCriteria("cn.edu.jmu.jyf.bean.Interest")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Interest instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Interest as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Interest> findByWeight(Object weight) {
		return findByProperty(WEIGHT, weight);
	}

	public List findAll() {
		log.debug("finding all Interest instances");
		try {
			String queryString = "from Interest";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Interest merge(Interest detachedInstance) {
		log.debug("merging Interest instance");
		try {
			Interest result = (Interest) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Interest instance) {
		log.debug("attaching dirty Interest instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Interest instance) {
		log.debug("attaching clean Interest instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InterestDAO getFromApplicationContext(ApplicationContext ctx) {
		return (InterestDAO) ctx.getBean("InterestDAO");
	}
}