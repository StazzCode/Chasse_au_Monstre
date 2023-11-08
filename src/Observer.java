import utils.Subject;

public interface Observer {

  public void update(Subject subj);
  public void update(Subject subj, Object data);

}
