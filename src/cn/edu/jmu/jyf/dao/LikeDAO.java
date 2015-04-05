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

import cn.edu.jmu.jyf.bean.Like;
import cn.edu.jmu.jyf.bean.LikeId;

/**
 * A data access object (DAO) providing persistence and search support for Like
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.edu.jmu.jyf.bean.Like
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class LikeDAO {
	private static final Logger log = LoggerFactory.getLogger(LikeDAO.class);
	// property constants

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

	public void save(Like transientInstance) {
		log.debug("saving Like instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Like persistentInstance) {
		log.debug("deleting Like instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Like findById(cn.edu.jmu.jyf.bean.LikeId id) {
		log.debug("getting Like instance with id: " + id);
		try {
			Like instance = (Like) getCurrentSession().get(
					"cn.edu.jmu.jyf.bean.Like", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Like> findByExample(Like instance) {
		log.debug("finding Like instance by example");
		try {
			List<Like> results = (List<Like>) getCurrentSession()
					.createCriteria("cn.edu.jmu.jyf.bean.Like")
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
		log.debug("finding Like instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Like as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Like instances");
		try {
			String queryString = "from Like";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Like merge(Like detachedInstance) {
		log.debug("merging Like instance");
		try {
			Like result = (Like) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Like instance) {
		log.debug("attaching dirty Like instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Like instance) {
		log.debug("attaching clean Like instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LikeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LikeDAO) ctx.getBean("LikeDAO");
	}
}