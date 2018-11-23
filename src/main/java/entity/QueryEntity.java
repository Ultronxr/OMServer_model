package entity;

public class QueryEntity {

    private String attribute;
    private String ext_id_from;
    private String ext_id_to;

    public QueryEntity(){

    }

    public QueryEntity(String attribute, String ext_id_from, String ext_id_to){
        this.attribute = attribute;
        this.ext_id_from = ext_id_from;
        this.ext_id_to = ext_id_to;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getExt_id_from() {
        return ext_id_from;
    }

    public void setExt_id_from(String ext_id_from) {
        this.ext_id_from = ext_id_from;
    }

    public String getExt_id_to() {
        return ext_id_to;
    }

    public void setExt_id_to(String ext_id_to) {
        this.ext_id_to = ext_id_to;
    }

    @Override
    public String toString(){
        return "?attribute="+this.attribute+"&ext_id_from="+this.ext_id_from+"&ext_id_to="+this.ext_id_to;
    }
}
