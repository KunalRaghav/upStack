package com.krsolutions.upstack.api.model;

import java.util.List;

public class questionResponse {

    List<questionJson> items;

    public List<questionJson> getItems() {
        return items;
    }

    public void setItems(List<questionJson> items) {
        this.items = items;
    }

    public class questionJson {
        String[] tags;
        Owner owner;
        Boolean is_answered;
        Integer view_count;
        Integer answer_count;
        Integer score;
        Integer last_activity_date;
        Integer creation_date;
        Integer last_edit_date;
        Integer question_id;
        String link;
        String title;

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public Boolean getIs_answered() {
            return is_answered;
        }

        public void setIs_answered(Boolean is_answered) {
            this.is_answered = is_answered;
        }

        public Integer getView_count() {
            return view_count;
        }

        public void setView_count(Integer view_count) {
            this.view_count = view_count;
        }

        public Integer getAnswer_count() {
            return answer_count;
        }

        public void setAnswer_count(Integer answer_count) {
            this.answer_count = answer_count;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Integer getLast_activity_date() {
            return last_activity_date;
        }

        public void setLast_activity_date(Integer last_activity_date) {
            this.last_activity_date = last_activity_date;
        }

        public Integer getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(Integer creation_date) {
            this.creation_date = creation_date;
        }

        public Integer getLast_edit_date() {
            return last_edit_date;
        }

        public void setLast_edit_date(Integer last_edit_date) {
            this.last_edit_date = last_edit_date;
        }

        public Integer getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(Integer question_id) {
            this.question_id = question_id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public class Owner {
            Integer reputation;
            Integer user_id;
            String user_type;
            String profile_image;
            String display_name;
            String link;

            public Integer getReputation() {
                return reputation;
            }

            public void setReputation(Integer reputation) {
                this.reputation = reputation;
            }

            public Integer getUser_id() {
                return user_id;
            }

            public void setUser_id(Integer user_id) {
                this.user_id = user_id;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(String profile_image) {
                this.profile_image = profile_image;
            }

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
