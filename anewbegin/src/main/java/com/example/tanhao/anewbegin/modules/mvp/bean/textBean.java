package com.example.tanhao.anewbegin.modules.mvp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class textBean {

    /**
     * success : true
     * data : [{"name":"TAKAMI 角质软化美容精华液  ","fileList":[{"uriPath":"306139971613530424962251967308/12/201703071635471298877120575082/7906075750061201253131638868700785013745200-200.jpg"}],"price":343,"id":184},{"name":"精灵高中MonsterHigh暮光之痕眼线笔防水不晕染  黑色","fileList":[{"uriPath":"988514797500929552930593366856/12/201701161320576823700406336238/69785195032228948871520627341812171186601.jpg"}],"price":86.62999725341797,"id":42},{"name":"YSL圣罗兰2017春季限量情挑诱吻唇膏3.5g 银管双色嘴唇夹心口红 6号色  ","fileList":[{"uriPath":"068609929986668258838145747446/12/201703061726261625678135936257/33798207979958416113392337879455673257406号-200-200.png"}],"price":340,"id":180},{"name":"精灵高中Monster High 极光单色眼影2.7g 美妆不晕染易上色轻盈细腻 M02金棕色","fileList":[{"uriPath":"244833878663083888877099038661/12/201701161357416186125616932113/64511020578548446544985976968586606094382.jpg"}],"price":78.7300033569336,"id":45},{"name":"Duit 手膜","fileList":[{"uriPath":"177452956555307725200202526005/12/201702241513154340452889117427/8067268945818362013988864713289523049608200-200.jpg"}],"price":90,"id":163},{"name":"精灵高中Monster High斑斓发粉 染发粉 一次性挑染不伤发染发剂易上色   玫红色","fileList":[{"uriPath":"221074123772760947292723201934/12/201701161158295617623091433461/10546181631978859577213342391200423081990.jpg"}],"price":70.83000183105469,"id":39},{"name":"YSL圣罗兰唇膏口红方管滋润持久保湿亮彩 17号色  直邮","fileList":[{"uriPath":"171428845559059513234905940598/12/201702201514438908249408420927/3266007154773225304286589198458559425139200.jpg"}],"price":340,"id":104},{"name":"精灵高中Monster High召唤口红笔 持久滋润保湿不脱色 桃红色","fileList":[{"uriPath":"465630283452059292851710356323/12/201701161120524327890368892159/73329380568445080875993409415792117072401.jpg"}],"price":94.52999877929688,"id":36},{"name":"KIEHL'S科颜氏维生素C紧肤精华乳液","fileList":[{"uriPath":"305905895623247177162375629938/12/201702171534415043568216725773/7507775579568809275069793440923337387265200-200.jpg"}],"price":552.5,"id":99},{"name":"精灵高中Monster High 尖叫睫毛膏 浓密卷翘 纤长防水","fileList":[{"uriPath":"112231651767942437586647529448/12/201701151906361222944307605659/7832186959253142345654918770148296460454睫毛膏.jpg"}],"price":118.2300033569336,"id":33}]
     * gsrArray : []
     * hsdArray : [{"name":"YSL"},{"name":"口红"},{"name":"韩国"},{"name":"面膜"},{"name":"是多少"}]
     */

    private boolean success;
    private List<DataBean> data;
    private List<?> gsrArray;
    private List<HsdArrayBean> hsdArray;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getGsrArray() {
        return gsrArray;
    }

    public void setGsrArray(List<?> gsrArray) {
        this.gsrArray = gsrArray;
    }

    public List<HsdArrayBean> getHsdArray() {
        return hsdArray;
    }

    public void setHsdArray(List<HsdArrayBean> hsdArray) {
        this.hsdArray = hsdArray;
    }

    public static class DataBean {
        /**
         * name : TAKAMI 角质软化美容精华液
         * fileList : [{"uriPath":"306139971613530424962251967308/12/201703071635471298877120575082/7906075750061201253131638868700785013745200-200.jpg"}]
         * price : 343
         * id : 184
         */

        private String name;
        private int price;
        private int id;
        private List<FileListBean> fileList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<FileListBean> getFileList() {
            return fileList;
        }

        public void setFileList(List<FileListBean> fileList) {
            this.fileList = fileList;
        }

        public static class FileListBean {
            /**
             * uriPath : 306139971613530424962251967308/12/201703071635471298877120575082/7906075750061201253131638868700785013745200-200.jpg
             */

            private String uriPath;

            public String getUriPath() {
                return uriPath;
            }

            public void setUriPath(String uriPath) {
                this.uriPath = uriPath;
            }
        }
    }

    public static class HsdArrayBean {
        /**
         * name : YSL
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
