package cn.edu.jmu.jyf.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.jmu.jyf.bean.Keyword;
import cn.edu.jmu.jyf.config.Config;

/**
 * A data access object (DAO) providing persistence and search support for
 * Keyword entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.jmu.jyf.bean.Keyword
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class KeywordDAO {
	private static final Logger log = LoggerFactory.getLogger(KeywordDAO.class);
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

	public void save(Keyword transientInstance) {
		log.debug("saving Keyword instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Keyword persistentInstance) {
		log.debug("deleting Keyword instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Keyword findById(cn.edu.jmu.jyf.bean.KeywordId id) {
		log.debug("getting Keyword instance with id: " + id);
		try {
			Keyword instance = (Keyword) getCurrentSession().get(
					"cn.edu.jmu.jyf.bean.Keyword", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Keyword> findByExample(Keyword instance) {
		log.debug("finding Keyword instance by example");
		try {
			List<Keyword> results = (List<Keyword>) getCurrentSession()
					.createCriteria("cn.edu.jmu.jyf.bean.Keyword")
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
		log.debug("finding Keyword instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Keyword as model where model."
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
		log.debug("finding all Keyword instances");
		try {
			String queryString = "from Keyword";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Keyword merge(Keyword detachedInstance) {
		log.debug("merging Keyword instance");
		try {
			Keyword result = (Keyword) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Keyword instance) {
		log.debug("attaching dirty Keyword instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Keyword instance) {
		log.debug("attaching clean Keyword instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KeywordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (KeywordDAO) ctx.getBean("KeywordDAO");
	}

	public List getByKeyword(Integer tagId, Long time, Integer amount) {
		try {
			String queryString = "from Keyword as k where k.tag.tagId=? "
					+ "and k.article.isHidden=0 and k.article.uploadDateTime<? "
					+ "order by k.article.uploadDateTime desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setInteger(0, tagId);
			queryObject.setTimestamp(1, new Date(time));
			queryObject.setMaxResults(amount);
			return queryObject.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List getHotByKeyword(Integer tagId, Integer amount, Integer begin) {
		try {
			String queryString = "from Keyword as k where k.tag.tagId=? "
					+ "and k.article.isHidden=0 order by ((k.article.likes.size*?"
					+ "+k.article.bookmarks.size*?+ k.article.readNumber*?)"
					+ "*TO_DAYS(k.article.uploadDateTime)*?) desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setInteger(0, tagId);
			queryObject.setInteger(1, Config.WEIGHT_OF_LIKE_IN_ARTICLE);
			queryObject.setInteger(2, Config.WEIGHT_OF_BOOKMARK_IN_ARTICLE);
			queryObject.setInteger(3, Config.WEIGHT_OF_READNUMBER_IN_ARTICLE);
			queryObject.setFloat(4, Config.WEIGHT_OF_TIME_IN_HOT);
			queryObject.setFirstResult(begin - 1);
			queryObject.setMaxResults(amount);
			return queryObject.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List getByKeywordAndQuality(Integer tagId, Integer amount,
			Integer begin) {
		try {
			String queryString = "from Keyword as k where k.tag.tagId=? and k.article.isHidden=0 order by"
					+ " (k.article.likes.size*?+k.article.bookmarks.size*?+"
					+ "k.article.readNumber*?) desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setInteger(0, tagId);
			queryObject.setInteger(1, Config.WEIGHT_OF_LIKE_IN_ARTICLE);
			queryObject.setInteger(2, Config.WEIGHT_OF_BOOKMARK_IN_ARTICLE);
			queryObject.setInteger(3, Config.WEIGHT_OF_READNUMBER_IN_ARTICLE);
			queryObject.setFirstResult(begin - 1);
			queryObject.setMaxResults(amount);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}